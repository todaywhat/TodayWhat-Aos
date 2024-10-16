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
        return try {
            setWidgetState(MealInfo.Loading)
            val request = getTodayMealsUseCase()
                .onSuccess {
                    setWidgetState(
                        when (getTimePeriod()) {
                            MealTime.Morning -> MealInfo.Available(
                                mealTime = "아침",
                                mealList = it.breakfast.first
                            )

                            MealTime.Lunch -> MealInfo.Available(
                                mealTime = "점심",
                                mealList = it.lunch.first
                            )

                            MealTime.Dinner -> MealInfo.Available(
                                mealTime = "저녁",
                                mealList = it.dinner.first
                            )
                        }
                    )
                }.onFailure {
                    setWidgetState(MealInfo.Unavailable)
                }
            if (request.isSuccess) Result.success() else Result.failure()
        } catch (e: Exception) {
            setWidgetState(MealInfo.Unavailable)
            Result.failure()
        }
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
}