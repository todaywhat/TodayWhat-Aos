package khs.onmi.main.screen

import android.content.pm.ApplicationInfo
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.core.common.android.EventLogger
import khs.onmi.core.common.android.Screen
import khs.onmi.main.viewmodel.MainViewModel

@Composable
fun MainRoute(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val activity = LocalActivity.current
    val context = LocalContext.current
    val isDebuggable = context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    var backPressedTime: Long = 0

    BackHandler {
        if (System.currentTimeMillis() - backPressedTime >= 2000) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(context, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            activity?.finish()
        }
    }

    LaunchedEffect(key1 = Unit) {
        EventLogger.pageShowed(Screen.MAIN)
    }

    val uiState = viewModel.container.stateFlow.collectAsState().value

    MainScreen(
        uiState = uiState,
        isDebuggable = isDebuggable,
        navigate = navController::navigate,
        reloadTimeTable = viewModel::reloadTimeTable,
        reloadMeal = viewModel::reloadMeals,
        onDebugDateSelected = viewModel::setDebugTargetDate,
        onDebugDateReset = viewModel::clearDebugTargetDate
    )
}