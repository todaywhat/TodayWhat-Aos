package com.onmi.widget.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.glance.material3.ColorProviders

object ONMIWidgetColorScheme {

    private val LightColorScheme = lightColorScheme(
        primary = Color(0xFF1F1F1F), // TextPrimary
        onPrimary = Color(0xFFFFFFFF), // BackgroundMain
        secondary = Color(0xFF3E3D3F), // TextSecondary
        onSecondary = Color(0xFFF8F8F8), // CardBackground,
        tertiary = Color(0xFF000000) // Black
    )

    private val DarkColorScheme = darkColorScheme(
        primary = Color(0xFFD8D8DF), // TextPrimary
        onPrimary = Color(0xFF000000), // BackgroundMain
        secondary = Color(0xFF84849A), // TextSecondary
        onSecondary = Color(0xFF18181D), // CardBackground,
        tertiary = Color(0xFFFFFFFF) // Black
    )

    val colors = ColorProviders(
        light = LightColorScheme,
        dark = DarkColorScheme
    )
}