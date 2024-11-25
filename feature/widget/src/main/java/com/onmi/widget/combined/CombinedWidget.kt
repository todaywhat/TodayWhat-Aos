package com.onmi.widget.combined

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
import androidx.glance.appwidget.cornerRadius
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
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import com.onmi.widget.theme.ONMIWidgetColorScheme
import com.onmi.widget.util.SuitText
import khs.onmi.root.MainActivity

class CombinedWidget : GlanceAppWidget() {
    override val stateDefinition = CombinedStateDefinition

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            LaunchedEffect(key1 = Unit) {
                CombinedWorker.enqueue(context)
            }

            GlanceTheme(colors = ONMIWidgetColorScheme.colors) {
                when (val state = currentState<CombinedInfo>()) {
                    is CombinedInfo.Available -> {
                        SuccessContent(
                            mealTime = state.mealTime,
                            mealList = state.mealList,
                            timeTable = state.subjectList,
                        )
                    }

                    is CombinedInfo.Loading -> {
                        LoadingContent()
                    }

                    is CombinedInfo.Unavailable -> {
                        UnavailableContent()
                    }
                }
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
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                SuitText(
                                    modifier = GlanceModifier.width(12.dp),
                                    text = "${index + 1}",
                                    color = GlanceTheme.colors.secondary.getColor(context),
                                    fontSize = 14.sp,
                                )
                                Spacer(modifier = GlanceModifier.width(2.dp))
                                SuitText(
                                    text = text,
                                    color = GlanceTheme.colors.tertiary.getColor(context),
                                    fontSize = 14.sp,
                                )
                            }
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

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .clickable(actionStartActivity<MainActivity>()),
            content = {}
        )
    }

    @Composable
    fun LoadingContent() {
        val context = LocalContext.current

        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(GlanceTheme.colors.onPrimary)
                .clickable(actionStartActivity<MainActivity>()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SuitText(
                text = "급식, 시간표 정보를 불러올 수 없습니다.",
                color = GlanceTheme.colors.tertiary.getColor(context),
                fontSize = 14.sp,
            )
        }
    }

    @Composable
    fun UnavailableContent() {
        val context = LocalContext.current

        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(GlanceTheme.colors.onPrimary)
                .clickable(actionStartActivity<MainActivity>()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SuitText(
                text = "급식, 시간표 정보 불러오는중..",
                color = GlanceTheme.colors.tertiary.getColor(context),
                fontSize = 14.sp,
            )
        }
    }
}

class CombinedWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = CombinedWidget()
}