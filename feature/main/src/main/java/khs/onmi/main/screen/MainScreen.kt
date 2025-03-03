package khs.onmi.main.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.DragInteraction
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.SelectedType
import khs.onmi.core.designsystem.component.InfoCard
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.SettingIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton
import khs.onmi.main.component.MainTabRow
import khs.onmi.main.component.MealsSection
import khs.onmi.main.component.MealsSectionState
import khs.onmi.main.component.TimeTableSection
import khs.onmi.main.viewmodel.container.MainState
import khs.onmi.navigation.ONMINavRoutes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    uiState: MainState,
    navigate: (route: String) -> Unit,
) {
    val pagerState = rememberPagerState()
    var dragStartPage by remember { mutableStateOf(pagerState.currentPage) }

    // 메인페이지 Pager 로깅 관련 코드
    LaunchedEffect(pagerState) {
        pagerState.interactionSource.interactions.collect { interaction ->
            when {
                interaction is DragInteraction.Start -> {
                    dragStartPage = pagerState.currentPage
                }

                (interaction is DragInteraction.Stop || interaction is DragInteraction.Cancel) && pagerState.currentPage != dragStartPage -> {
                    if (pagerState.currentPage == 0) EventLogger.selectedMealTab(SelectedType.SWIPED)
                    else EventLogger.selectTimeTableTab(SelectedType.SWIPED)
                }
            }
        }
    }

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
                        trailing = {
                            WrappedIconButton(onClick = { navigate(ONMINavRoutes.SETTING) }) {
                                SettingIcon(tint = color.Black)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(21.dp))
                    InfoCard(
                        modifier = Modifier
                            .height(95.dp)
                            .padding(horizontal = 16.dp),
                        isMeal = pagerState.currentPage == 0,
                        school = uiState.schoolName,
                        grade = uiState.grade,
                        `class` = uiState.`class`,
                        date = uiState.targetDate
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
                        state = MealsSectionState.Success,
                        breakfast = uiState.breakfast,
                        lunch = uiState.lunch,
                        dinner = uiState.dinner
                    )

                    1 -> TimeTableSection(
                        state = TimeTableSection.Success,
                        timeTableList = uiState.timetable
                    )
                }
            }
        }
    }
}
