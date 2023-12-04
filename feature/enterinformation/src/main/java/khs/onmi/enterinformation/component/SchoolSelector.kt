package khs.onmi.enterinformation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun SchoolSelector(
    schools: List<Pair<String, String>>,
    onItemClick: (idx: Int) -> Unit,
) {
    LazyColumn {
        itemsIndexed(schools) { idx, item ->
            SchoolSelectorItem(
                school = item.first,
                location = item.second,
                onClick = {
                    onItemClick(idx)
                }
            )
        }
    }
}

@Preview
@Composable
fun SchoolSelectorPre() {

    val dummy = listOf(
        Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
        Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
        Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
        Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
        Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
        Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
    )

    ONMITheme { color, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color.White)
        ) {
            SchoolSelector(
                schools = dummy,
                onItemClick = {}
            )
        }
    }
}