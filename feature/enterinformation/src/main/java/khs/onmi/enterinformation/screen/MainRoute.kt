package khs.onmi.enterinformation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.enterinformation.model.CurrentState
import khs.onmi.enterinformation.viewmodel.EnterInformationViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainRoute(
    navController: NavController,
    viewModel: EnterInformationViewModel = hiltViewModel(),
) {
    val uiState = viewModel.container.stateFlow.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
            when (sideEffect) {
                else -> {}
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.container.stateFlow.collectLatest { uiState ->
            viewModel.setCurrentState(
                currentState = when {
                    uiState.school.isNotBlank() && uiState.grade.isNotEmpty() && uiState.`class`.isNotEmpty() && uiState.department.isNotEmpty() && !uiState.schoolSelectorVisible -> CurrentState.FINISH
                    uiState.school.isNotEmpty() && uiState.grade.isNotEmpty() && uiState.`class`.isNotEmpty() && !uiState.schoolSelectorVisible -> CurrentState.ENTERDEPARTMENT
                    uiState.school.isNotBlank() && uiState.grade.isNotEmpty() && !uiState.schoolSelectorVisible -> CurrentState.ENTERCLASS
                    uiState.school.isNotBlank() && !uiState.schoolSelectorVisible -> CurrentState.ENTERGRADE
                    else -> CurrentState.ENTERSCHOOL
                }
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.container.stateFlow.collectLatest { uiState ->
            viewModel.onGreetingValueChange(
                when (uiState.currentState) {
                    CurrentState.ENTERSCHOOL -> Pair("", "")
                    CurrentState.ENTERGRADE -> Pair("몇학년 이신가요?", "")
                    CurrentState.ENTERCLASS -> Pair("몇반 이신가요?", "")
                    CurrentState.ENTERDEPARTMENT -> Pair("특정 학과에 다니시나요?", "학과는 없으면 안해도 괜찮아요!")
                    CurrentState.FINISH -> Pair("입력하신 정보가 정확한가요?", "정보는 설정에서 언제든지 변경할 수 있어요.")
                }
            )
        }
    }

    with(viewModel) {
        MainScreen(
            uiState = uiState,
            setSchoolSelectorVisible = ::setSchoolSelectorVisible,
            setDepartmentSelectorVisible = ::setDepartmentSelectorVisible,
            setCurrentState = ::setCurrentState,
            onSchoolValueChange = ::onSchoolValueChange,
            onGradeValueChange = ::onGradeValueChange,
            onClassValueChange = ::onClassValueChange,
            onDepartmentValueChange = ::onDepartmentValueChange,
            sendSchoolSearchRequest = ::searchSchoolByName,
            onBackButtonClick = {},
            onFinishButtonClick = {}
        )
    }
}