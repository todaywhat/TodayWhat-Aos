package khs.onmi.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.TimeTableItem

@Composable
fun TimeTableSection(timeTableList: List<String>) {
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

@Preview
@Composable
fun TimeTableSectionPre() {
    val timeTableList = listOf("국어", "영어", "수학", "사회", "과학", "기술가정", "역사", "한문")
    TimeTableSection(timeTableList)
}