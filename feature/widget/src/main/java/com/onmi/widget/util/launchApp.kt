package com.onmi.widget.util

import android.content.Intent
import android.net.Uri
import androidx.glance.action.Action
import androidx.glance.appwidget.action.actionStartActivity

// actionStartActivity는 명시 Intent를 PendingIntent로 직접 변환해 ActionTrampolineActivity를 거치지 않는다.
// `clickable { ... }`(LambdaAction)은 trampoline을 경유하는데 일부 OEM/OS 조합에서 trampoline intent가
// extras를 잃어 IllegalArgumentException("List adapter activity trampoline ...")로 크래시한다.
fun launchAppAction(): Action = actionStartActivity(
    Intent(Intent.ACTION_VIEW, Uri.parse("onmi://root"))
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
)
