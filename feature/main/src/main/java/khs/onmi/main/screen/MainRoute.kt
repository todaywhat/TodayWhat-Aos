package khs.onmi.main.screen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
    val activity = LocalContext.current as Activity
    var backPressedTime: Long = 0

    BackHandler {
        if (System.currentTimeMillis() - backPressedTime >= 2000) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(activity, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            activity.finish()
        }
    }

    LaunchedEffect(key1 = Unit) {
        EventLogger.pageShowed(Screen.MAIN)
    }

    val uiState = viewModel.container.stateFlow.collectAsState().value

    MainScreen(
        uiState = uiState,
        navigate = navController::navigate,
        reloadTimeTable = viewModel::getTodayTimeTable,
        reloadMeal = viewModel::getTodayMeals
    )
}