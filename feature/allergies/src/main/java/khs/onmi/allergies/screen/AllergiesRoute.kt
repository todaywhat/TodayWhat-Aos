package khs.onmi.allergies.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.allergies.viewmodel.AllergiesViewModel
import khs.onmi.allergies.viewmodel.container.AllergiesSideEffect
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AllergiesRoute(
    navController: NavController,
    viewModel: AllergiesViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState = viewModel.collectAsState().value

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is AllergiesSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    AllergiesScreen(
        uiState = uiState,
        onBackPressed = { navController.popBackStack() },
        onAllergyToggle = viewModel::toggleAllergy,
    )
}
