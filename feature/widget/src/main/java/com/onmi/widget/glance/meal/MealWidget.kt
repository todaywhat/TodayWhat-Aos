package com.onmi.widget.glance.meal

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import com.onmi.widget.glance.theme.ONMIWidgetColorScheme
import com.onmi.widget.glance.util.SuitText

class MealWidget : GlanceAppWidget() {
    override val stateDefinition = MealInfoStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val dummy = listOf(
                "친환경백미찹쌀밥",
                "매콤어묵우국",
                "청포묵무침",
                "닭갈비",
                "치즈소떡소떡&양념소스",
                "배추김치",
                "상큼이주스",
                "닭가슴살양상추샐러드&오리엔탈소스"
            )

            GlanceTheme(colors = ONMIWidgetColorScheme.colors) {
                SuccessContent(
                    mealTime = "아침",
                    mealList = dummy
                )
            }
        }
    }

    @Composable
    private fun SuccessContent(
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
                Column {
                    SuitText(
                        text = item,
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

    @Composable
    fun LoadingContent() {
        val context = LocalContext.current

        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(GlanceTheme.colors.onPrimary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SuitText(
                text = "급식 정보를 불러올 수 없습니다.",
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
                .background(GlanceTheme.colors.onPrimary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SuitText(
                text = "급식 정보 불러오는중..",
                color = GlanceTheme.colors.tertiary.getColor(context),
                fontSize = 14.sp,
            )
        }
    }
}

class MealWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = MealWidget()
}