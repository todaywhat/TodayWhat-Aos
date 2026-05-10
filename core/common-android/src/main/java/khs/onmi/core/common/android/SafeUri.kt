package khs.onmi.core.common.android

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.safeOpenUri(
    uri: String,
    addNewTaskFlag: Boolean = false,
    onError: ((Throwable) -> Unit)? = null,
): Boolean {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri)).apply {
        if (addNewTaskFlag) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    return runCatching { startActivity(intent) }
        .onFailure { onError?.invoke(it) }
        .isSuccess
}
