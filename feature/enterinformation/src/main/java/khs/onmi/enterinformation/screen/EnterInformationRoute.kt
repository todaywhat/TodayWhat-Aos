package khs.onmi.enterinformation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import khs.onmi.enterinformation.model.CurrentState
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

    LaunchedEffect(key1 = viewModel.school) {
        delay(500)
        viewModel.searchSchoolByName(viewModel.school)
    }

    LaunchedEffect(
        keys = arrayOf(
            viewModel.school,
            viewModel.grade,
            viewModel.`class`,
            viewModel.department
        )
    ) {
        viewModel.setCurrentState(
            currentState = when {
                viewModel.school.isNotBlank() && viewModel.grade.isNotEmpty() && viewModel.`class`.isNotEmpty() && viewModel.department.isNotEmpty() && !uiState.schoolSelectorVisible -> CurrentState.FINISH
                viewModel.school.isNotEmpty() && viewModel.grade.isNotEmpty() && viewModel.`class`.isNotEmpty() && !uiState.schoolSelectorVisible -> CurrentState.ENTERDEPARTMENT
                viewModel.school.isNotBlank() && viewModel.grade.isNotEmpty() && !uiState.schoolSelectorVisible -> CurrentState.ENTERCLASS
                viewModel.school.isNotBlank() && !uiState.schoolSelectorVisible -> CurrentState.ENTERGRADE
                else -> CurrentState.ENTERSCHOOL
            }
        )
    }

    LaunchedEffect(key1 = uiState.currentState) {
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

    with(viewModel) {
        EnterInformationScreen(
            uiState = uiState,
            school = school,
            grade = grade,
            department = department,
            `class` = `class`,
            setSchoolSelectorVisible = ::setSchoolSelectorVisible,
            setDepartmentSelectorVisible = ::setDepartmentSelectorVisible,
            setCurrentState = ::setCurrentState,
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
            onBackButtonClick = {},
            onFinishButtonClick = {
                saveEnteredUserInfo(
                    schoolCode = uiState.schoolList.find { it.schoolName == school }
                        ?.schoolCode
                        ?: "",
                    educationCode = uiState.schoolList.find { it.schoolName == school }
                        ?.educationCode
                        ?: "",
                    schoolName = school,
                    grade = grade.toInt(),
                    `class` = `class`.toInt(),
                    department = department,
                    schoolType = uiState.schoolList.find { it.schoolName == school }
                        ?.schoolType
                        ?: "",
                )
            }
        )
    }
}