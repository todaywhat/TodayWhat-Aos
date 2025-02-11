package khs.onmi.setting.screen.addwidget

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.onmi.widget.combined.CombinedWidgetReceiver
import com.onmi.widget.meal.MealWidgetReceiver
import com.onmi.widget.timetable.TimeTableWidgetReceiver
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.WidgetFamily
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton
import khs.onmi.setting.component.addwidget.AddWidgetItem
import kotlinx.coroutines.launch
import khs.onmi.core.designsystem.R as DR

enum class Widget(
    @DrawableRes val previewImage: Int,
    val widgetName: String,
    val widgetSize: String,
) {
    COMBINED(
        previewImage = DR.drawable.combined_widget_preview,
        widgetName = "급식&시간표",
        widgetSize = "4 x 2",
    ),
    SMALL_MEAL(
        previewImage = DR.drawable.small_meal_widget_preview,
        widgetName = "급식",
        widgetSize = "2 x 2",
    ),
    SMALL_TIME_TABLE(
        previewImage = DR.drawable.small_time_table_widget_preview,
        widgetName = "시간표",
        widgetSize = "2 x 2",
    ),
}

@Composable
fun AddWidgetScreen(
    onBackPressed: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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
                itemsIndexed(Widget.entries) { _, item ->
                    AddWidgetItem(
                        previewImage = item.previewImage,
                        widgetName = item.widgetName,
                        widgetSize = item.widgetSize,
                    ) {
                        coroutineScope.launch {
                            GlanceAppWidgetManager(
                                context = context,
                            ).requestPinGlanceAppWidget(
                                receiver = when (item) {
                                    Widget.COMBINED -> {
                                        EventLogger.clickAddToWidgetType(WidgetFamily.MEAL_AND_TIMETABLE_MEDIUM)
                                        CombinedWidgetReceiver::class.java
                                    }

                                    Widget.SMALL_MEAL -> {
                                        EventLogger.clickAddToWidgetType(WidgetFamily.MEAL_SMALL)
                                        MealWidgetReceiver::class.java
                                    }

                                    Widget.SMALL_TIME_TABLE -> {
                                        EventLogger.clickAddToWidgetType(WidgetFamily.TIMETABLE_SMALL)
                                        TimeTableWidgetReceiver::class.java
                                    }
                                },
                                successCallback = PendingIntent.getBroadcast(
                                    context,
                                    0,
                                    Intent(context, SuccessLoggerReceiver::class.java).apply {
                                        putExtra(
                                            "widget", when (item) {
                                                Widget.COMBINED -> WidgetFamily.MEAL_AND_TIMETABLE_MEDIUM.value
                                                Widget.SMALL_MEAL -> WidgetFamily.MEAL_SMALL.value
                                                Widget.SMALL_TIME_TABLE -> WidgetFamily.TIMETABLE_SMALL.value
                                            }
                                        )
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

class SuccessLoggerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val widget = intent?.getStringExtra("widget") ?: return

        Log.d("WidgetLogger", "위젯 추가 성공! $widget")
    }
}