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
import com.onmi.domain.usecase.meal.GetMealsUseCase
import com.onmi.widget.timetable.TimeTableWidget
import com.onmi.widget.util.MealInfoState
import com.onmi.widget.util.WidgetDataDisplayManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.WidgetFamily
import khs.onmi.core.common.android.WidgetKind
import java.time.Duration

@HiltWorker
class MealWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workParams: WorkerParameters,
    private var getMealsUseCase: GetMealsUseCase,
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
            newState = WidgetDataDisplayManager.fetchMealInfo(
                getMealsUseCase = getMealsUseCase,
                requestedMealTime = WidgetDataDisplayManager.getCurrentMealTime()
            ).toMealInfo()
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

    private fun MealInfoState.toMealInfo(): MealInfo = when (this) {
        is MealInfoState.Available -> MealInfo.Available(mealTime, mealList)
        is MealInfoState.Unavailable -> MealInfo.Unavailable
        is MealInfoState.Loading -> MealInfo.Loading
    }
}
