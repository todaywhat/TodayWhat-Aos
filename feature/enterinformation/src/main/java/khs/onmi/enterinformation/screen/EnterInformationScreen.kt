package khs.onmi.enterinformation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ColumnSpacer
import khs.onmi.core.designsystem.component.ONMIButton
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton
import khs.onmi.core.ui.LabelTextFiled
import khs.onmi.enterinformation.component.DepartmentSelectorBottomSheet
import khs.onmi.enterinformation.component.GreetingComponent
import khs.onmi.enterinformation.component.SchoolSelector
import khs.onmi.enterinformation.model.CurrentState
import khs.onmi.enterinformation.viewmodel.container.EnterInformationState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterInformationScreen(
    uiState: EnterInformationState,
    school: String,
    department: String,
    grade: String,
    `class`: String,
    setSchoolSelectorVisible: (visible: Boolean) -> Unit,
    setDepartmentSelectorVisible: (visible: Boolean) -> Unit,
    setCurrentState: (state: CurrentState) -> Unit,
    onSchoolValueChange: (school: String) -> Unit,
    onGradeValueChange: (grade: String) -> Unit,
    onClassValueChange: (`class`: String) -> Unit,
    onDepartmentValueChange: (department: String) -> Unit,
    onSchoolItemClick: (educationCode: String, schoolCode: String) -> Unit,
    onBackButtonClick: () -> Unit,
    onFinishButtonClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ONMITheme { color, _ ->
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            containerColor = color.BackgroundMain,
            topBar = {
                TopNavigationBar(
                    leading = {
                        WrappedIconButton(onClick = onBackButtonClick) {
                            ArrowBackIcon(tint = color.Black)
                        }
                    }
                )
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = uiState.currentState == CurrentState.ENTERDEPARTMENT || uiState.currentState == CurrentState.FINISH,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }
                ) {
                    ONMIButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(52.dp),
                        text = if (uiState.currentState == CurrentState.ENTERDEPARTMENT) "이대로하기" else "확인!",
                        isEnabled = true,
                        onClick = onFinishButtonClick
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            top = it.calculateTopPadding() + 16.dp
                        )
                    ),
            ) {
                AnimatedVisibility(
                    modifier = Modifier.padding(bottom = 24.dp),
                    visible = uiState.currentState != CurrentState.ENTERSCHOOL,
                    enter = slideInVertically(),
                    exit = slideOutVertically()
                ) {
                    GreetingComponent(greetings = Pair(uiState.greetingTitle, uiState.greetingBody))
                }
                AnimatedVisibility(
                    modifier = Modifier.padding(bottom = 24.dp),
                    visible = uiState.currentState == CurrentState.ENTERDEPARTMENT || uiState.currentState == CurrentState.FINISH
                ) {
                    LabelTextFiled(
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = "학과",
                        value = department,
                        placeHolderText = "학과를 선택해주세요.",
                        isReadOnly = true,
                        onClick = {
                            setDepartmentSelectorVisible(true)
                        },
                        onTrailingIconClick = {
                            onDepartmentValueChange("")
                        },
                        imeAction = ImeAction.Done,
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        })
                    )
                }
                AnimatedVisibility(
                    modifier = Modifier.padding(bottom = 24.dp),
                    visible = uiState.currentState == CurrentState.ENTERCLASS || uiState.currentState == CurrentState.ENTERDEPARTMENT || uiState.currentState == CurrentState.FINISH
                ) {
                    LabelTextFiled(
                        modifier = Modifier.fillMaxWidth(),
                        label = "반",
                        value = `class`,
                        onValueChange = onClassValueChange,
                        placeHolderText = "반을 입력해주세요.",
                        onTrailingIconClick = {
                            onClassValueChange("")
                        },
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.NumberPassword,
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Up)
                        })
                    )
                }
                AnimatedVisibility(
                    modifier = Modifier.padding(bottom = 24.dp),
                    visible = uiState.currentState != CurrentState.ENTERSCHOOL
                ) {
                    LabelTextFiled(
                        modifier = Modifier.fillMaxWidth(),
                        label = "학년",
                        value = grade,
                        onValueChange = onGradeValueChange,
                        placeHolderText = "학년을 입력해주세요.",
                        onTrailingIconClick = {
                            onGradeValueChange("")
                        },
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next,
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Up)
                        })
                    )
                }
                LabelTextFiled(
                    modifier = Modifier.fillMaxWidth(),
                    label = "학교이름",
                    value = school,
                    placeHolderText = "학교이름을 입력해주세요.",
                    onValueChange = onSchoolValueChange,
                    onClick = {
                        setSchoolSelectorVisible(true)
                        setCurrentState(CurrentState.ENTERSCHOOL)
                    },
                    onTrailingIconClick = {
                        onSchoolValueChange("")
                    },
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Up)
                    })
                )
                AnimatedVisibility(visible = uiState.schoolSelectorVisible) {
                    ColumnSpacer(dp = 8.dp)
                    SchoolSelector(
                        schools = uiState.schoolList.map { school ->
                            Pair(
                                school.schoolName,
                                school.schoolLocation
                            )
                        },
                        onItemClick = { idx ->
                            onSchoolValueChange(uiState.schoolList[idx].schoolName)
                            setSchoolSelectorVisible(false)
                            onSchoolItemClick(
                                uiState.schoolList[idx].educationCode,
                                uiState.schoolList[idx].schoolCode
                            )
                            focusManager.clearFocus()
                        }
                    )
                }
            }

            if (uiState.departmentSelectorVisible) {
                DepartmentSelectorBottomSheet(
                    departments = uiState.departmentList,
                    sheetState = sheetState,
                    selectedItemIdx = uiState.departmentList.indexOf(department),
                    onItemClick = { idx ->
                        onDepartmentValueChange(uiState.departmentList[idx])
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                setDepartmentSelectorVisible(false)
                                focusManager.moveFocus(FocusDirection.Up)
                            }
                        }
                    },
                    onDismissRequest = {
                        setDepartmentSelectorVisible(false)
                        focusManager.clearFocus()
                    }
                )
            }
        }
    }
}