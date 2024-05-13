package com.onmi.widget.combined

import android.content.Intent
import android.widget.RemoteViewsService

class CombinedWidgetTimeTableRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return CombinedWidgetTimeTableRemoteViewsFactory(this.applicationContext)
    }
}