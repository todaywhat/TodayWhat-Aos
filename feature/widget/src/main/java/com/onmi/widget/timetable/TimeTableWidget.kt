package com.onmi.widget.timetable

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import com.onmi.widget.components.MessageBox
import com.onmi.widget.theme.ONMIWidgetColorScheme
import com.onmi.widget.util.SuitText
import com.onmi.widget.util.launchApp

class TimeTableWidget : GlanceAppWidget() {
    override val stateDefinition = TimeTableInfoStateDefinition

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            LaunchedEffect(key1 = Unit) {
                TimeTableWorker.enqueue(context)
            }

            GlanceTheme(colors = ONMIWidgetColorScheme.colors) {
                when (val state = currentState<TimeTableInfo>()) {
                    is TimeTableInfo.Available -> {
                        TimeTableWidgetContent(timeTable = state.timeTableData)
                    }

                    is TimeTableInfo.Loading -> {
                        MessageBox(message = "시간표 정보 불러오는중..")
                    }

                    is TimeTableInfo.Unavailable -> {
                        MessageBox(message = "시간표 정보를 불러올 수 없습니다.")
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeTableWidgetContent(timeTable: List<String>) {
    val context = LocalContext.current

    LazyColumn(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(12.dp)
            .background(GlanceTheme.colors.onPrimary)
    ) {
        itemsIndexed(timeTable) { index, item ->
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SuitText(
                        modifier = GlanceModifier.width(12.dp),
                        text = "${index + 1}",
                        color = GlanceTheme.colors.secondary.getColor(context),
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = GlanceModifier.width(2.dp))
                    SuitText(
                        text = item,
                        color = GlanceTheme.colors.tertiary.getColor(context),
                        fontSize = 14.sp,
                    )
                }
                if (timeTable.lastIndex != index) {
                    Spacer(modifier = GlanceModifier.height(6.68.dp))
                }
            }
        }
    }

    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .clickable { context.launchApp() },
        content = {}
    )
}

class TimeTableWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = TimeTableWidget()
}