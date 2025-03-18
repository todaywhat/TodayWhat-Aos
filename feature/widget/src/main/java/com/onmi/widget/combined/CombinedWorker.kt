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
import com.onmi.domain.usecase.meal.GetMealsUseCase
import com.onmi.domain.usecase.timetable.GetTimeTableState
import com.onmi.domain.usecase.timetable.GetTimeTableUseCase
import com.onmi.widget.util.MealInfoState
import com.onmi.widget.util.WidgetDataDisplayManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration

@HiltWorker
class CombinedWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workParams: WorkerParameters,
    private var getMealsUseCase: GetMealsUseCase,
    private var getTimeTableUseCase: GetTimeTableUseCase,
) : CoroutineWorker(context, workParams) {
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
    override suspend fun doWork(): Result {
        val todayTimeTable = getTimeTableUseCase()

        if (todayTimeTable is GetTimeTableState.Failure) {
            setWidgetState(CombinedInfo.Unavailable)
            return Result.success()
        }

        val currentMealTime = WidgetDataDisplayManager.getCurrentMealTime()
        val mealsInfo = WidgetDataDisplayManager.fetchMealInfo(
            getMealsUseCase = getMealsUseCase,
            requestedMealTime = currentMealTime
        )

        setWidgetState(
            if (mealsInfo is MealInfoState.Available) {
                CombinedInfo.Available(
                    mealTime = mealsInfo.mealTime,
                    mealList = mealsInfo.mealList,
                    subjectList = (todayTimeTable as GetTimeTableState.Success).response
                )
            } else CombinedInfo.Unavailable
        )

        return Result.success()
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