package com.onmi.widget.meal

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
import com.onmi.widget.timetable.TimeTableWidget
import com.onmi.widget.util.MealTime
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.WidgetFamily
import khs.onmi.core.common.android.WidgetKind
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

@HiltWorker
class MealWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workParams: WorkerParameters,
    private var getTodayMealsUseCase: GetTodayMealsUseCase,
) : CoroutineWorker(context, workParams) {

    init {
        EventLogger.widgetConfiguration(WidgetFamily.SYSTEM_SMALL, WidgetKind.MEAL)
    }

    companion object {
        private val uniqueWorkName = MealWorker::class.java.simpleName

        @RequiresApi(Build.VERSION_CODES.O)
        fun enqueue(context: Context) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = PeriodicWorkRequestBuilder<MealWorker>(
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
    override suspend fun doWork(): Result {
        setWidgetState(
            fetchMealInfo(
                getTodayMealsUseCase = getTodayMealsUseCase,
                requestedMealTime = getTimePeriod()
            )
        )

        return Result.success()
    }

    private suspend fun setWidgetState(newState: MealInfo) {
        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(MealWidget::class.java)
        glanceIds.forEach { glanceId ->
            updateAppWidgetState(
                context = context,
                definition = MealInfoStateDefinition,
                glanceId = glanceId,
                updateState = { newState }
            )
        }
        TimeTableWidget().updateAll(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun fetchMealInfo(
        getTodayMealsUseCase: GetTodayMealsUseCase,
        requestedMealTime: MealTime,
    ): MealInfo {
        getTodayMealsUseCase()
            .onSuccess { mealsInfo ->
                val mealTimes = listOf(MealTime.Morning, MealTime.Lunch, MealTime.Dinner)
                val startIndex = mealTimes.indexOf(requestedMealTime)

                (startIndex..mealTimes.lastIndex).forEach { index ->
                    val currentMealTime = mealTimes[index]
                    val currentMeal = when (currentMealTime) {
                        MealTime.Morning -> mealsInfo.breakfast
                        MealTime.Lunch -> mealsInfo.lunch
                        MealTime.Dinner -> mealsInfo.dinner
                    }

                    if (currentMeal.first.isNotEmpty()) {
                        return MealInfo.Available(
                            convertTimeToString(currentMealTime),
                            currentMeal.first
                        )
                    }
                }
            }

        val nextDay = LocalDate.now().plusDays(1)

        getTodayMealsUseCase(targetDate = nextDay)
            .onSuccess { nextMealsInfo ->
                val mealTimes = listOf(MealTime.Morning, MealTime.Lunch, MealTime.Dinner)

                (0..mealTimes.lastIndex).forEach { index ->
                    val currentMealTime = mealTimes[index]
                    val currentMeal = when (currentMealTime) {
                        MealTime.Morning -> nextMealsInfo.breakfast
                        MealTime.Lunch -> nextMealsInfo.lunch
                        MealTime.Dinner -> nextMealsInfo.dinner
                    }

                    if (currentMeal.first.isNotEmpty()) {
                        return MealInfo.Available(
                            convertTimeToString(currentMealTime),
                            currentMeal.first
                        )
                    }
                }
            }

        // 요청된 시간대부터 이후의 모든 시간대의 급식이 비어있는 경우
        return MealInfo.Available(
            mealTime = convertTimeToString(requestedMealTime),
            mealList = emptyList()
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTimePeriod(): MealTime {
        val currentTime = LocalTime.now()
        val morningEnd = LocalTime.of(7, 35)
        val lunchEnd = LocalTime.of(12, 35)

        return when {
            currentTime.isBefore(morningEnd) -> MealTime.Morning
            currentTime.isBefore(lunchEnd) -> MealTime.Lunch
            else -> MealTime.Dinner
        }
    }

    private fun convertTimeToString(time: MealTime): String {
        return when (time) {
            MealTime.Morning -> "아침"
            MealTime.Lunch -> "점심"
            MealTime.Dinner -> "저녁"
        }
    }
}
