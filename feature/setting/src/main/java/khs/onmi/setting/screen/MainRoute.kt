package khs.onmi.setting.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.Screen

@Composable
fun MainRoute(
    navController: NavController,
) {
    LaunchedEffect(key1 = Unit) {
        EventLogger.pageShowed(Screen.SETTING)
    }

    MainScreen(
        onBackPressed = { navController.popBackStack() }
    )
}