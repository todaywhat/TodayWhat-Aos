package khs.onmi.enterinformation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.enterinformation.viewmodel.EnterInformationViewModel
import khs.onmi.enterinformation.viewmodel.container.EnterInformationSideEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EnterInformationRoute(
    navController: NavController,
    viewModel: EnterInformationViewModel = hiltViewModel(),
) {
    val uiState = viewModel.container.stateFlow.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
            when (sideEffect) {
                is EnterInformationSideEffect.Navigate -> {
                    navController.navigate(sideEffect.route)
                }
            }
        }
    }

    LaunchedEffect(key1 = uiState.school) {
        delay(500)
        viewModel.searchSchoolByName(uiState.school)
    }

    with(viewModel) {
        EnterInformationScreen(
            uiState = uiState,
            setSchoolSelectorVisible = ::setSchoolSelectorVisible,
            setDepartmentSelectorVisible = ::setDepartmentSelectorVisible,
            onSchoolValueChange = ::onSchoolValueChange,
            onGradeValueChange = ::onGradeValueChange,
            onClassValueChange = ::onClassValueChange,
            onDepartmentValueChange = ::onDepartmentValueChange,
            onSchoolItemClick = { educationCode, schoolCode ->
                viewModel.getSchoolDepartments(
                    educationCode = educationCode,
                    schoolCode = schoolCode,
                )
            },
            onBackButtonClick = navController::navigateUp,
            onFinishButtonClick = ::saveEnteredUserInfo,
        )
    }
}