package com.onmi.widget.glance.timetable

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text

class TimeTableWidget : GlanceAppWidget() {

    override val stateDefinition = TimeTableInfoStateDefinition

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            when (val state = currentState<TimeTableInfo>()) {
                is TimeTableInfo.Available -> {
                    SuccessContent(timeTable = state.timeTableData)
                }

                is TimeTableInfo.Loading -> {
                    LoadingContent()
                }

                is TimeTableInfo.Unavailable -> {
                    UnavailableContent()
                }
            }

            LaunchedEffect(key1 = Unit) {
                TimeTableWorker.enqueue(context)
            }
        }
    }

    @Composable
    private fun SuccessContent(timeTable: List<String>) {
        LazyColumn(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(12.dp)
                .background(Color.White),
        ) {
            itemsIndexed(timeTable) { index, item ->
                Column {
                    Row {
                        Text(
                            text = "${index + 1}",
                        )
                        Spacer(modifier = GlanceModifier.width(2.dp))
                        Text(
                            text = item,
                        )
                    }
                    if (timeTable.lastIndex != index) {
                        Spacer(modifier = GlanceModifier.height(6.68.dp))
                    }
                }
            }
        }
    }

    @Composable
    fun LoadingContent() {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "시간표 정보를 불러올 수 없습니다.")
        }
    }

    @Composable
    fun UnavailableContent() {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "시간표 정보 불러오는중..")
        }
    }
}

class TimeTableWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = TimeTableWidget()
}