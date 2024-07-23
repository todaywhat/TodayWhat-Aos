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
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration
import java.time.LocalTime

@HiltWorker
class MealWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workParams: WorkerParameters,
    private var getTodayMealsUseCase: GetTodayMealsUseCase,
) : CoroutineWorker(context, workParams) {

    companion object {
        private val uniqueWorkName = MealWorker::class.java.simpleName

        @RequiresApi(Build.VERSION_CODES.O)
        fun enqueue(context: Context, force: Boolean = false) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = PeriodicWorkRequestBuilder<MealWorker>(
                Duration.ofMinutes(30)
            )
            var workPolicy = ExistingPeriodicWorkPolicy.KEEP

            if (force) {
                workPolicy = ExistingPeriodicWorkPolicy.REPLACE
            }

            manager.enqueueUniquePeriodicWork(
                uniqueWorkName,
                workPolicy,
                requestBuilder.build()
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(uniqueWorkName)
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
                            "MORNING" -> MealInfo.Available(
                                mealTime = it.breakfast.second,
                                mealList = it.breakfast.first
                            )

                            "LUNCH" -> MealInfo.Available(
                                mealTime = it.lunch.second,
                                mealList = it.lunch.first
                            )

                            "DINNER" -> MealInfo.Available(
                                mealTime = it.dinner.second,
                                mealList = it.dinner.first
                            )

                            else -> MealInfo.Unavailable
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
    private fun getTimePeriod(): String {
        val currentTime = LocalTime.now()
        val morningEnd = LocalTime.of(7, 35)
        val lunchEnd = LocalTime.of(12, 35)

        return when {
            currentTime.isBefore(morningEnd) -> "MORNING"
            currentTime.isBefore(lunchEnd) -> "LUNCH"
            else -> "DINNER"
        }
    }
}