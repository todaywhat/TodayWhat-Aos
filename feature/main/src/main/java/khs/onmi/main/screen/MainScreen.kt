package khs.onmi.main.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.InfoCard
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.main.component.MainTabRow
import khs.onmi.main.component.MealsSection
import khs.onmi.main.component.TimeTableSection

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState()

    ONMITheme { color, typography ->
        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    TopNavigationBar(
                        leading = {
                            Text(
                                text = "오늘뭐임",
                                style = typography.Headline3,
                                color = color.Black
                            )
                        },
                        trailing = { /*TODO: 설정, 알림 기능 추가 후 아이콘 추가*/ }
                    )
                    Spacer(modifier = Modifier.height(21.dp))
                    InfoCard(
                        modifier = Modifier
                            .height(95.dp)
                            .padding(horizontal = 16.dp),
                        isMeal = pagerState.currentPage == 0,
                        school = "광주소프트웨어마이스터고",
                        grade = 3,
                        `class` = 2
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    MainTabRow(
                        pagerState = pagerState,
                        tabs = listOf("급식", "시간표")
                    )
                }
            },
            containerColor = color.BackgroundMain
        ) { paddingValues ->
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                state = pagerState,
                pageCount = 2
            ) { index ->
                when (index) {
                    0 -> MealsSection(
                        breakfast = listOf("쌀밥"),
                        lunch = null,
                        dinner = null
                    )

                    1 -> TimeTableSection(
                        timeTableList = listOf(
                            Pair("1", "한국사"),
                            Pair("2", "한국사"),
                            Pair("3", "한국사"),
                            Pair("4", "한국사"),
                            Pair("5", "한국사"),
                            Pair("6", "한국사"),
                            Pair("7", "한국사"),
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPre() {
    MainScreen()
}