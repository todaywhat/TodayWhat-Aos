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
import com.onmi.domain.usecase.timetable.GetTimeTableState
import com.onmi.domain.usecase.timetable.GetTimeTableUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration

@HiltWorker
class TimeTableWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workParams: WorkerParameters,
    private var getTimeTableUseCase: GetTimeTableUseCase,
) : CoroutineWorker(context, workParams) {
    companion object {
        private val uniqueWorkName = TimeTableWorker::class.java.simpleName

        @RequiresApi(Build.VERSION_CODES.O)
        fun enqueue(context: Context) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = PeriodicWorkRequestBuilder<TimeTableWorker>(
                Duration.ofMinutes(30)
            )

            manager.enqueueUniquePeriodicWork(
                uniqueWorkName,
                ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                requestBuilder.build()
            )
        }
    }

    override suspend fun doWork(): Result {
        return try {
            setWidgetState(TimeTableInfo.Loading)
            val request = getTimeTableUseCase()

            when (request) {
                is GetTimeTableState.Failure -> setWidgetState(TimeTableInfo.Unavailable)
                is GetTimeTableState.Success -> setWidgetState(TimeTableInfo.Available(request.response))
            }

            if (request is GetTimeTableState.Success) Result.success() else Result.failure()
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