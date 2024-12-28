package khs.onmi.setting.screen.addwidget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton
import khs.onmi.setting.component.addwidget.AddWidgetItem
import khs.onmi.core.designsystem.R as DR

@Composable
fun AddWidgetScreen(
    onBackPressed: () -> Unit,
) {
    val widgetList = listOf(
        Triple(DR.drawable.combined_widget_preview, "급식&시간표", "4 x 2"),
        Triple(DR.drawable.small_meal_widget_preview, "급식", "2 x 2"),
        Triple(DR.drawable.small_time_table_widget_preview, "시간표", "2 x 2"),
    )

    ONMITheme { color, _ ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = color.BackgroundSecondary,
            topBar = {
                TopNavigationBar(
                    title = "위젯 추가",
                    leading = {
                        WrappedIconButton(onClick = onBackPressed) {
                            ArrowBackIcon(tint = color.Black)
                        }
                    }
                )
            },
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 24.dp)
            ) {
                itemsIndexed(widgetList) { _, item ->
                    AddWidgetItem(
                        previewImage = item.first,
                        widgetName = item.second,
                        widgetSize = item.third,
                    ) {
                        // todo: Click 이벤트 구현
                    }
                }
            }
        }
    }
}