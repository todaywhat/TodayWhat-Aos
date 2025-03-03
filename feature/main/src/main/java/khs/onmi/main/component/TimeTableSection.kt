package khs.onmi.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ONMIButton
import khs.onmi.core.designsystem.component.TimeTableItem
import khs.onmi.core.designsystem.theme.ONMITheme

// todo State 수정 필요
sealed interface TimeTableSection {
    data object Success : TimeTableSection
    data object Failure : TimeTableSection
    data object Empty : TimeTableSection
}

@Composable
fun TimeTableSection(
    state: TimeTableSection,
    timeTableList: List<String>
) {
    when(state) {
        TimeTableSection.Success -> {
            TimeTableSectionItem(timeTableList = timeTableList)
        }
        TimeTableSection.Empty -> {
            TimeTableSectionEmptyItem {  }
        }
        TimeTableSection.Failure -> {
            TimeTableSectionErrorItem {  }
        }
    }
}

@Composable
fun TimeTableSectionItem(timeTableList: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { Spacer(modifier = Modifier.height(32.dp)) }
        itemsIndexed(timeTableList) { index, subject ->
            TimeTableItem(
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                time = (index + 1).toString(),
                subject = subject
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
fun TimeTableSectionEmptyItem(onClick: () -> Unit) {
    ONMITheme { color, typography ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "시간표 정보가 없습니다.",
                style = typography.Body1,
                color = color.TextSecondary,
            )
            Spacer(modifier = Modifier.height(16.dp))
            ONMIButton(
                isEnabled = true,
                text = "문의하기",
                colors = ButtonDefaults.buttonColors(
                    contentColor = color.Black,
                    containerColor = color.White,
                ),
                onClick = onClick
            )
        }
    }
}

@Composable
fun TimeTableSectionErrorItem(onClick: () -> Unit) {
    ONMITheme { color, typography ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // todo 에러 정의 후 수정 필요
            if (false) {
                Text(
                    text = "인터넷이 연결되지 않았습니다.\n와이파이 혹은 데이터를 연결 해주세요.",
                    style = typography.Body1,
                    color = color.TextSecondary,
                    textAlign = TextAlign.Center
                )
// todo 별도 에러케이스로 분리
//                Text(
//                    text = "3월에는 학교의 임시 시간표 사용으로\n정보가 표시되지 않을 수 있습니다.",
//                    style = typography.Body1,
//                    color = color.TextSecondary,
//                    textAlign = TextAlign.Center
//                )
            } else {
                Text(
                    text = "시간표 정보를 불러오지 못했습니다.",
                    style = typography.Body1,
                    color = color.TextSecondary,
                )
                Spacer(modifier = Modifier.height(16.dp))
                ONMIButton(
                    isEnabled = true,
                    text = "다시 불러오기",
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color.Black,
                        containerColor = color.White,
                    ),
                    onClick = onClick
                )
            }
        }
    }
}

@Preview
@Composable
fun TimeTableSectionPre() {
    val timeTableList = listOf("국어", "영어", "수학", "사회", "과학", "기술가정", "역사", "한문")
    TimeTableSection(
        state = TimeTableSection.Success,
        timeTableList = timeTableList
    )
}