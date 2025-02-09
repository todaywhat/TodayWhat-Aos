package com.onmi.widget.meal

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
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import com.onmi.widget.components.MessageBox
import com.onmi.widget.theme.ONMIWidgetColorScheme
import com.onmi.widget.util.SuitText

class MealWidget : GlanceAppWidget() {
    override val stateDefinition = MealInfoStateDefinition

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            LaunchedEffect(key1 = Unit) {
                MealWorker.enqueue(context)
            }

            GlanceTheme(colors = ONMIWidgetColorScheme.colors) {
                when (val state = currentState<MealInfo>()) {
                    is MealInfo.Available -> {
                        MealWidgetContent(
                            mealTime = state.mealTime,
                            mealList = state.mealList
                        )
                    }

                    is MealInfo.Loading -> {
                        MessageBox(message = "급식 정보 불러오는중..")
                    }

                    is MealInfo.Unavailable -> {
                        MessageBox(message = "급식 정보를 불러올 수 없습니다.")
                    }
                }
            }
        }
    }

    @Composable
    private fun MealWidgetContent(
        mealTime: String,
        mealList: List<String>,
    ) {
        val context = LocalContext.current

        LazyColumn(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(12.dp)
                .background(GlanceTheme.colors.onPrimary),
        ) {
            item {
                SuitText(
                    text = "[$mealTime]",
                    color = GlanceTheme.colors.primary.getColor(context),
                    fontSize = 14.sp
                )
                Spacer(modifier = GlanceModifier.height(1.37.dp))
            }
            itemsIndexed(mealList) { index, item ->
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

        Box(
            modifier = GlanceModifier
                .fillMaxSize(),
//                .clickable(actionStartActivity<MainActivity>()),
            content = {}
        )
    }
}

class MealWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = MealWidget()
}