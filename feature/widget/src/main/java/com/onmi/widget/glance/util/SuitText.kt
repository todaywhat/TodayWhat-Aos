package com.onmi.widget.glance.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext

@Composable
fun SuitText(
    text: String,
    fontSize: TextUnit,
    modifier: GlanceModifier = GlanceModifier,
    color: Color = Color.Black,
    letterSpacing: TextUnit = 0.1.sp
) {
    Image(
        modifier = modifier,
        provider = ImageProvider(
            LocalContext.current.textAsBitmap(
                text = text,
                fontSize = fontSize,
                color = color,
                font = khs.onmi.core.designsystem.R.font.suit_bold,
                letterSpacing = letterSpacing.value
            )
        ),
        contentDescription = null,
    )
}