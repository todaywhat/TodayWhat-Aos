package com.onmi.widget.meal

import android.content.Intent
import android.widget.RemoteViewsService

class MealWidgetRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return MealRemoteViewsFactory(this.applicationContext)
    }
}