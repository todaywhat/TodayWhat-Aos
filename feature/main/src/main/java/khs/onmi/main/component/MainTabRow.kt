package khs.onmi.main.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.SelectedType
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.main.util.customTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainTabRow(
    tabs: List<String>,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    ONMITheme { color, typography ->
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .customTabIndicatorOffset(
                            tabPositions[pagerState.currentPage],
                            146.dp
                        ),
                    color = color.Black // 색상 지정
                )
            },
            containerColor = color.BackgroundMain
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = {
                        Text(
                            text = tab,
                            style = typography.Headline4,
                            color = if (index == pagerState.currentPage) color.Black else color.UnselectedPrimary
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        if (index == 0) EventLogger.selectedMealTab(SelectedType.TAPPED)
                        else EventLogger.selectTimeTableTab(SelectedType.TAPPED)

                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = color.TextSecondary
                )
            }
        }
    }
}