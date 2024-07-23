package com.onmi.widget.timetable

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
import com.onmi.domain.usecase.timetable.GetTodayTimeTableUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration

@HiltWorker
class TimeTableWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workParams: WorkerParameters,
    private var getTimeTableUseCase: GetTodayTimeTableUseCase
) : CoroutineWorker(context, workParams) {

    companion object {
        private val uniqueWorkName = TimeTableWorker::class.java.simpleName

        @RequiresApi(Build.VERSION_CODES.O)
        fun enqueue(context: Context, force: Boolean = false) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = PeriodicWorkRequestBuilder<TimeTableWorker>(
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

    override suspend fun doWork(): Result {
        return try {
            setWidgetState(TimeTableInfo.Loading)
            val request = getTimeTableUseCase()
                .onSuccess {
                    setWidgetState(TimeTableInfo.Available(it))
                }.onFailure {
                    setWidgetState(TimeTableInfo.Unavailable)
                }
            if (request.isSuccess) Result.success() else Result.failure()
        } catch (e: Exception) {
            setWidgetState(TimeTableInfo.Unavailable)
            Result.failure()
        }
    }

    private suspend fun setWidgetState(newState: TimeTableInfo) {
        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(TimeTableWidget::class.java)
        glanceIds.forEach { glanceId ->
            updateAppWidgetState(
                context = context,
                definition = TimeTableInfoStateDefinition,
                glanceId = glanceId,
                updateState = { newState }
            )
        }
        TimeTableWidget().updateAll(context)
    }
}