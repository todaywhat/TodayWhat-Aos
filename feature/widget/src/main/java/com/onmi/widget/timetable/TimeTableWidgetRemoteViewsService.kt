package com.onmi.widget.timetable

import android.content.Intent
import android.widget.RemoteViewsService

class TimeTableWidgetRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return TimeTableRemoteViewsFactory(this.applicationContext)
    }
}