package com.onmi.widget.combined

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.onmi.domain.usecase.meal.GetTodayMealsUseCase
import com.onmi.domain.usecase.timetable.GetTodayTimeTableUseCase
import com.onmi.widget.util.MealTime
import com.onmi.widget.util.WidgetDataDisplayManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.WidgetFamily
import khs.onmi.core.common.android.WidgetKind
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.time.Duration

@HiltWorker
class CombinedWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workParams: WorkerParameters,
    private var getTodayMealsUseCase: GetTodayMealsUseCase,
    private var getTodayTimeTableUseCase: GetTodayTimeTableUseCase,
) : CoroutineWorker(context, workParams) {

    init {
        EventLogger.widgetConfiguration(WidgetFamily.SYSTEM_MEDIUM, WidgetKind.MEAL_TIMETABLE)
    }

    companion object {
        private val uniqueWorkName = CombinedWorker::class.java.simpleName

        @RequiresApi(Build.VERSION_CODES.O)
        fun enqueue(context: Context) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = PeriodicWorkRequestBuilder<CombinedWorker>(
                Duration.ofMinutes(30)
            )

            manager.enqueueUniquePeriodicWork(
                uniqueWorkName,
                ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                requestBuilder.build()
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val getTodayMealsRequest = async { getTodayMealsUseCase() }
            val getTodayTimeTableRequest = async { getTodayTimeTableUseCase() }

            val getTodayMealsResponse = getTodayMealsRequest.await()
            val getTodayTimeTableResponse = getTodayTimeTableRequest.await()

            if (getTodayMealsResponse.isFailure || getTodayTimeTableResponse.isFailure) Result.failure()

            val todayMeals =
                getTodayMealsResponse.getOrNull() ?: return@coroutineScope Result.failure()
            val todayTimeTable =
                getTodayTimeTableResponse.getOrNull() ?: return@coroutineScope Result.failure()

            WidgetDataDisplayManager.fetchMealInfo(
                getTodayMealsUseCase = getTodayMealsUseCase,
                requestedMealTime = WidgetDataDisplayManager.getCurrentMealTime()
            )

            setWidgetState(
                CombinedInfo.Available(
                    mealTime = when (WidgetDataDisplayManager.getCurrentMealTime()) {
                        MealTime.Morning -> "아침"

                        MealTime.Lunch -> "점심"

                        MealTime.Dinner -> "저녁"
                    },
                    mealList = when (WidgetDataDisplayManager.getCurrentMealTime()) {
                        MealTime.Morning -> todayMeals.breakfast.first

                        MealTime.Lunch -> todayMeals.lunch.first

                        MealTime.Dinner -> todayMeals.dinner.first
                    },
                    subjectList = todayTimeTable
                )
            )

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private suspend fun setWidgetState(newState: CombinedInfo) {
        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(CombinedWidget::class.java)
        glanceIds.forEach { glanceId ->
            updateAppWidgetState(
                context = context,
                definition = CombinedStateDefinition,
                glanceId = glanceId,
                updateState = { newState }
            )
        }
        CombinedWidget().updateAll(context)
    }
}