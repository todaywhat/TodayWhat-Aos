package khs.onmi.setting.screen.addwidget

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AddWidgetRoute(
    navController: NavController,
) {
    AddWidgetScreen { navController.popBackStack() }
}