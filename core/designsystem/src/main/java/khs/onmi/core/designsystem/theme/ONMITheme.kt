package khs.onmi.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import khs.onmi.core.designsystem.theme.color.ColorTheme
import khs.onmi.core.designsystem.theme.color.DarkThemeColor
import khs.onmi.core.designsystem.theme.color.WhiteThemeColor
import khs.onmi.core.designsystem.theme.font.ONMIFont

@Composable
fun ONMITheme(
    content: @Composable (color: ColorTheme, typography: ONMIFont) -> Unit
) {
    content(if (isSystemInDarkTheme()) DarkThemeColor else WhiteThemeColor, ONMIFont)
}