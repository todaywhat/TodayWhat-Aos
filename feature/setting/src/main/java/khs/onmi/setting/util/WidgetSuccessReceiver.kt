package khs.onmi.setting.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import khs.onmi.core.common.android.EventLogger

class WidgetSuccessReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val widgetType = intent?.getStringExtra("widgetType") ?: return

        EventLogger.completeAddToWidget(widgetType)
    }
}