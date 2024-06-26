package com.onmi.widget.glance.combined

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import com.onmi.widget.glance.theme.ONMIWidgetColorScheme
import com.onmi.widget.glance.util.SuitText

class CombinedWidget : GlanceAppWidget() {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme(colors = ONMIWidgetColorScheme.colors) {
                SuccessContent(
                    timeTable = listOf(
                        "일본어",
                        "일본어",
                        "웹 프로그래밍 실무",
                        "웹 프로그래밍 실무",
                        "스마트문화체험",
                        "인공지능실무",
                        "스마트문화체험",
                        "인공지능실무"
                    ),
                    mealTime = "아침",
                    mealList = listOf(
                        "친환경 백미밥",
                        "매콤어묵무국",
                        "청포묵무침",
                        "닭갈비",
                        "치즈소떡소떡",
                        "배추김치",
                        "상큼이주스",
                        "닭가슴살양상추볶음"
                    ),
                )
            }
        }
    }

    @Composable
    private fun SuccessContent(
        mealTime: String,
        mealList: List<String>,
        timeTable: List<String>,
    ) {
        val context = LocalContext.current

        Row(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(GlanceTheme.colors.onPrimary)
                .padding(8.dp)
                .cornerRadius(16.dp)
        ) {
            LazyColumn(
                modifier = GlanceModifier
                    .background(GlanceTheme.colors.onSecondary)
                    .defaultWeight()
                    .fillMaxHeight()
                    .cornerRadius(8.dp)
                    .padding(8.dp)
            ) {
                item {
                    Column {
                        SuitText(text = "시간표", fontSize = 16.sp)
                        Spacer(modifier = GlanceModifier.height(8.dp))
                    }
                }
                itemsIndexed(timeTable) { index, item ->
                    if (item.isNotEmpty()) {
                        val text = if (item.length >= 14 && item.isNotEmpty()) item.substring(
                            0,
                            13
                        ) + "..." else item

                        Column {
                            SuitText(
                                text = text,
                                color = GlanceTheme.colors.tertiary.getColor(context),
                                fontSize = 14.sp
                            )
                            if (mealList.lastIndex != index) {
                                Spacer(modifier = GlanceModifier.height(1.37.dp))
                            }
                        }
                    }
                }
            }
            Spacer(modifier = GlanceModifier.width(8.dp))
            LazyColumn(
                modifier = GlanceModifier
                    .background(GlanceTheme.colors.onSecondary)
                    .defaultWeight()
                    .fillMaxHeight()
                    .cornerRadius(8.dp)
                    .padding(8.dp)
            ) {
                item {
                    Column {
                        SuitText(text = mealTime, fontSize = 16.sp)
                        Spacer(modifier = GlanceModifier.height(8.dp))
                    }
                }
                itemsIndexed(mealList) { index, item ->
                    if (item.isNotEmpty()) {
                        val text = if (item.length >= 14) item.substring(0, 13) + "..." else item

                        Column {
                            SuitText(
                                text = text,
                                color = GlanceTheme.colors.tertiary.getColor(context),
                                fontSize = 14.sp
                            )
                            if (mealList.lastIndex != index) {
                                Spacer(modifier = GlanceModifier.height(1.37.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

class CombinedWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = CombinedWidget()
}