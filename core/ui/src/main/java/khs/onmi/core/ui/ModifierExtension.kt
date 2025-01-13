package khs.onmi.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

inline fun Modifier.clickable(
    color: Color = Color.Unspecified,
    crossinline onClick: () -> Unit
): Modifier = composed {
    this then clickable(
        indication = rememberRipple(color = color, bounded = true),
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}