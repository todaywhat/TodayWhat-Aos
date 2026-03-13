package khs.onmi.allergies.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.allergies.viewmodel.AllergiesViewModel
import khs.onmi.allergies.viewmodel.container.AllergiesSideEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AllergiesRoute(
    navController: NavController,
    viewModel: AllergiesViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState = viewModel.container.stateFlow.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                is AllergiesSideEffect.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    AllergiesScreen(
        uiState = uiState,
        onBackPressed = { navController.popBackStack() },
    )
}
