package com.onmi.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import com.onmi.widget.util.SuitText
import com.onmi.widget.util.launchApp

@Composable
fun MessageBox(message: String) {
    val context = LocalContext.current

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(GlanceTheme.colors.onPrimary)
            .clickable { context.launchApp() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SuitText(
            text = message,
            color = GlanceTheme.colors.tertiary.getColor(context),
            fontSize = 14.sp,
        )
    }
}