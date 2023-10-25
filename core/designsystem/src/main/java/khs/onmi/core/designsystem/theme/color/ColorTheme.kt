package khs.onmi.core.designsystem.theme.color

import androidx.compose.ui.graphics.Color

abstract class ColorTheme {
    abstract val TextPrimary: Color
    abstract val TextSecondary: Color
    abstract val UnselectedPrimary: Color
    abstract val UnselectedSecondary: Color
    abstract val CardBackground: Color
    abstract val CardBackgroundSecondary: Color
    abstract val BackgroundMain: Color
    abstract val BackgroundSecondary: Color
    abstract val White: Color
    abstract val Black: Color
    abstract val LightBox: Color

    //System Color
    internal val Point = Color(0xFFFF5959)
    internal val Success = Color(0xFF4B79EF)
    internal val AbsoluteWhite = Color(0xFFFFFFFF)
    internal val AbsoluteBlack = Color(0xFF000000)
}