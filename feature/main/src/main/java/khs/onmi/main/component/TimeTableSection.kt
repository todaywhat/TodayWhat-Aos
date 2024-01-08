package khs.onmi.main.component

import androidx.compose.foundation.layout.Arrangement
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
fun TimeTableSection(timeTableList: List<Pair<String, String>>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(timeTableList) { _, item ->
            TimeTableItem(
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                time = item.first,
                subject = item.second
            )
        }
    }
}

@Preview
@Composable
fun TimeTableSectionPre() {
    val timeTableList = listOf(
        Pair("1", "한국사"),
        Pair("2", "한국사"),
        Pair("3", "한국사"),
        Pair("4", "한국사"),
        Pair("5", "한국사"),
        Pair("6", "한국사"),
        Pair("7", "한국사"),
    )
    TimeTableSection(timeTableList)
}