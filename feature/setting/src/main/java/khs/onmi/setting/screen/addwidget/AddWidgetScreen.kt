package khs.onmi.setting.screen.addwidget

import android.app.PendingIntent
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.onmi.widget.combined.CombinedWidgetReceiver
import com.onmi.widget.meal.MealWidgetReceiver
import com.onmi.widget.timetable.TimeTableWidgetReceiver
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.WidgetType
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton
import khs.onmi.setting.component.addwidget.AddWidgetItem
import khs.onmi.setting.util.WidgetSuccessReceiver
import kotlinx.coroutines.launch
import khs.onmi.core.designsystem.R as DR

enum class Widget(
    @DrawableRes val previewImage: Int,
    val widgetName: String,
    val widgetSize: String,
    val widgetType: WidgetType,
    val widgetReceiverClass: Class<out GlanceAppWidgetReceiver>,
) {
    COMBINED(
        previewImage = DR.drawable.combined_widget_preview,
        widgetName = "급식&시간표",
        widgetSize = "4 x 2",
        widgetType = WidgetType.MEAL_AND_TIMETABLE_MEDIUM,
        widgetReceiverClass = CombinedWidgetReceiver::class.java
    ),
    SMALL_MEAL(
        previewImage = DR.drawable.small_meal_widget_preview,
        widgetName = "급식",
        widgetSize = "2 x 2",
        widgetType = WidgetType.MEAL_SMALL,
        widgetReceiverClass = MealWidgetReceiver::class.java
    ),
    SMALL_TIME_TABLE(
        previewImage = DR.drawable.small_time_table_widget_preview,
        widgetName = "시간표",
        widgetSize = "2 x 2",
        widgetType = WidgetType.TIMETABLE_SMALL,
        widgetReceiverClass = TimeTableWidgetReceiver::class.java
    ),
}

@Composable
fun AddWidgetScreen(
    onBackPressed: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    ONMITheme { color, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color.BackgroundSecondary)
                .safeDrawingPadding()
        ) {
            TopNavigationBar(
                title = "위젯 추가",
                leading = {
                    WrappedIconButton(onClick = onBackPressed) {
                        ArrowBackIcon(tint = color.Black)
                    }
                }
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 24.dp)
            ) {
                itemsIndexed(Widget.entries) { _, item ->
                    AddWidgetItem(
                        previewImage = item.previewImage,
                        widgetName = item.widgetName,
                        widgetSize = item.widgetSize,
                    ) {
                        EventLogger.clickAddToWidgetType(item.widgetType)
                        coroutineScope.launch {
                            GlanceAppWidgetManager(
                                context = context,
                            ).requestPinGlanceAppWidget(
                                receiver = item.widgetReceiverClass,
                                successCallback = PendingIntent.getBroadcast(
                                    context,
                                    0,
                                    Intent(context, WidgetSuccessReceiver::class.java).apply {
                                        putExtra("widgetType", item.widgetType.value)
                                    },
                                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
