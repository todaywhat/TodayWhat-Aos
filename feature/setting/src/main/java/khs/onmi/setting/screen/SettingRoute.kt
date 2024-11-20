package khs.onmi.setting.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.Screen
import khs.onmi.navigation.ONMINavRoutes
import khs.onmi.setting.viewmodel.SettingViewModel
import khs.onmi.setting.viewmodel.container.SettingSideEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SettingRoute(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState = viewModel.container.stateFlow.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                is SettingSideEffect.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        EventLogger.pageShowed(Screen.SETTING)
    }

    LaunchedEffect(key1 = uiState.isSkipWeekend) {
        delay(300)
        viewModel.setIsSkipWeekend(uiState.isSkipWeekend)
    }

    SettingScreen(
        uiState = uiState,
        onBackPressed = { navController.popBackStack() },
        onEnterInformationClick = { navController.navigate(ONMINavRoutes.EnterInformation.MAIN) },
        onSkipWeekendToggleValueChanged = viewModel::onSkipWeekendToggleValueChanged,
        onShowNextDayInfoAfterDinnerValueChanged = viewModel::onShowNextDayInfoAfterDinnerValueChanged,
    )
}