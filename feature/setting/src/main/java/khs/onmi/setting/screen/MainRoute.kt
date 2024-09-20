package khs.onmi.setting.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.Screen
import khs.onmi.setting.viewmodel.SettingViewModel

@Composable
fun MainRoute(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val uiState = viewModel.container.stateFlow.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        EventLogger.pageShowed(Screen.SETTING)
    }

    MainScreen(
        uiState = uiState,
        onBackPressed = { navController.popBackStack() }
    )
}