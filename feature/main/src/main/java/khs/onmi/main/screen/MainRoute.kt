package khs.onmi.main.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.main.viewmodel.MainViewModel

@Composable
fun MainRoute(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val uiState = viewModel.container.stateFlow.collectAsState().value

    MainScreen(uiState = uiState)
}