package khs.onmi.setting.screen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.icon.CalendarIcon
import khs.onmi.core.designsystem.icon.PaperIcon
import khs.onmi.core.designsystem.icon.RiceIcon
import khs.onmi.core.designsystem.icon.RightArrowIcon
import khs.onmi.core.designsystem.icon.SchoolIcon
import khs.onmi.core.designsystem.icon.WidgetAddIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton
import khs.onmi.setting.component.RoundedWhiteBox
import khs.onmi.setting.component.SettingListComponent
import khs.onmi.setting.component.ToggleItem
import khs.onmi.setting.model.SettingItemsData
import khs.onmi.setting.util.WebLink
import khs.onmi.setting.viewmodel.container.SettingState

@Composable
fun SettingScreen(
    uiState: SettingState,
    onBackPressed: () -> Unit,
    onEnterInformationClick: () -> Unit,
    onAddWidgetClick: () -> Unit,
    onSkipWeekendToggleValueChanged: (value: Boolean) -> Unit,
    onShowNextDayInfoAfterDinnerValueChanged: (value: Boolean) -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    val view = LocalView.current

    ONMITheme { color, typography ->
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = color.BackgroundSecondary.toArgb()
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = color.BackgroundSecondary,
            topBar = {
                TopNavigationBar(
                    leading = {
                        WrappedIconButton(onClick = onBackPressed) {
                            ArrowBackIcon(tint = color.Black)
                        }
                    }
                )
            },
        ) {
            Column(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding().value.dp,
                    start = 15.dp,
                    end = 15.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "설정",
                    style = typography.Headline1,
                    color = color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                RoundedWhiteBox(onClick = onEnterInformationClick) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        SchoolIcon(tint = color.Black)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "${uiState.grade}학년 ${uiState.`class`}반",
                            style = typography.Caption1,
                            color = color.TextSecondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = uiState.schoolName,
                            style = typography.Body3,
                            color = color.TextPrimary
                        )
                    }
                }
                RoundedWhiteBox {
                    SettingListComponent(
                        items = listOf(
                            SettingItemsData(
                                trailing = { RightArrowIcon(tint = color.UnselectedPrimary) },
                                text = "위젯 추가",
                                leading = { WidgetAddIcon(tint = color.Black) },
                                onClick = onAddWidgetClick
                            ),
                            SettingItemsData(
                                trailing = { RightArrowIcon(tint = color.UnselectedPrimary) },
                                text = "이용 약관",
                                leading = { PaperIcon(tint = color.Black) },
                                onClick = { uriHandler.openUri(WebLink.PolicyUrl) }
                            )
                        )
                    )
                }
                RoundedWhiteBox {
                    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                        ToggleItem(
                            icon = { RiceIcon(tint = color.Black) },
                            title = "저녁 후 내일 급식 표시",
                            value = uiState.isShowNextDayInfoAfterDinner,
                            onValueChange = onShowNextDayInfoAfterDinnerValueChanged
                        )
                        ToggleItem(
                            icon = { CalendarIcon(tint = color.Black) },
                            title = "주말 건너뛰기",
                            value = uiState.isSkipWeekend,
                            onValueChange = onSkipWeekendToggleValueChanged
                        )
                    }
                }
            }
        }
    }
}
