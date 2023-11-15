package khs.onmi.core.designsystem.utils

internal inline fun <T> T.runIf(condition: Boolean, block: T.() -> T) =
    if (condition) block() else this