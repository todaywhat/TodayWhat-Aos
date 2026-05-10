package com.onmi.widget.util

import android.content.Context
import khs.onmi.core.common.android.safeOpenUri

fun Context.launchApp() {
    safeOpenUri(uri = "onmi://root", addNewTaskFlag = true)
}
