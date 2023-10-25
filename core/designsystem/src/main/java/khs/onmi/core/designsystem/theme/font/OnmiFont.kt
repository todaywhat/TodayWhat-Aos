package khs.onmi.core.designsystem.theme.font

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import khs.onmi.core.designsystem.R

object ONMIFont {
    private val suit = FontFamily(
        Font(R.font.suit_bold, FontWeight.Bold),
        Font(R.font.suit_medium, FontWeight.Medium)
    )

    @Stable
    val Headline1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    )

    @Stable
    val Headline2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )

    @Stable
    val Headline3 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )

    @Stable
    val Headline4 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )

    @Stable
    val Body1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    @Stable
    val Body2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 21.sp
    )

    @Stable
    val Body3 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 21.sp
    )

    @Stable
    val Caption1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
}