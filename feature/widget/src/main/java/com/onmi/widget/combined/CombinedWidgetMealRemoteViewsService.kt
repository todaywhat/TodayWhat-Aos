package com.onmi.widget.combined

import android.content.Intent
import android.widget.RemoteViewsService

class CombinedWidgetMealRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return CombinedWidgetMealRemoteViewsFactory(this.applicationContext)
    }
}