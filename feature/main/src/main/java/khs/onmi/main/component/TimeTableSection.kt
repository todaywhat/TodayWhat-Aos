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
import com.onmi.domain.usecase.timetable.TimeTableException
import com.onmi.domain.usecase.timetable.TimeTableState
import khs.onmi.core.designsystem.component.LoadingLottie
import khs.onmi.core.designsystem.component.ONMIButton
import khs.onmi.core.designsystem.component.TimeTableItem
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun TimeTableSection(
    timeTableState: TimeTableState,
    onReloadClick: () -> Unit,
) {
    when (timeTableState) {
        is TimeTableState.Success -> {
            TimeTableSectionItem(timeTableList = timeTableState.response)
        }

        is TimeTableState.Failure -> {
            TimeTableSectionErrorItem(
                timeTableException = timeTableState.exception,
                onReloadClick = onReloadClick
            )
        }

        TimeTableState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingLottie()
            }
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
fun TimeTableSectionErrorItem(
    timeTableException: TimeTableException,
    onReloadClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (timeTableException) {
                TimeTableException.DataEmpty -> {
                    Text(
                        text = "시간표 정보가 없습니다.",
                        style = typography.Body1,
                        color = color.TextSecondary,
                    )
                    /*TODO("문의하기 기능 추가 후 버튼 활성화")
                    Spacer(modifier = Modifier.height(16.dp))
                    ONMIButton(
                        isEnabled = true,
                        text = "문의하기",
                        colors = ButtonDefaults.buttonColors(
                            contentColor = color.Black,
                            containerColor = color.White,
                        ),
                        onClick = onReloadClick
                    )*/
                }

                TimeTableException.InternetDisconnected -> {
                    Text(
                        text = "인터넷이 연결되지 않았습니다.\n와이파이 혹은 데이터를 연결 해주세요.",
                        style = typography.Body1,
                        color = color.TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }

                TimeTableException.TemporaryTimeTable -> {
                    Text(
                        text = "3월에는 학교의 임시 시간표 사용으로\n정보가 표시되지 않을 수 있습니다.",
                        style = typography.Body1,
                        color = color.TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }

                is TimeTableException.Unknown -> {
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
                            contentColor = color.White,
                            containerColor = color.Black,
                        ),
                        onClick = onReloadClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TimeTableSectionPre() {
    val timeTableList = listOf("국어", "영어", "수학", "사회", "과학", "기술가정", "역사", "한문")
    TimeTableSection(
        timeTableState = TimeTableState.Success(
            response = timeTableList
        ),
        onReloadClick = {}
    )
}