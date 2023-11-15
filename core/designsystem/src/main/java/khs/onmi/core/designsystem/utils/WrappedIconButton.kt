package khs.onmi.core.designsystem.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import khs.onmi.core.designsystem.modifier.onmiClickable

@Composable
fun WrappedIconButton(onClick: (() -> Unit)?, icon: (@Composable () -> Unit)?) {
    icon?.let {
        Box(
            modifier = Modifier.onmiClickable(
                bounded = false,
                onClick = onClick
            )
        ) {
            icon()
        }
    }
}