package khs.onmi.enterinformation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import khs.onmi.core.designsystem.component.ONMIButton
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton
import khs.onmi.core.ui.LabelTextFiled
import khs.onmi.enterinformation.component.DepartmentSelectorBottomSheet
import khs.onmi.enterinformation.component.GreetingComponent
import kotlinx.coroutines.launch

sealed class CurrentStage {
    object ENTERSCHOOL : CurrentStage()

    object ENTERGRADE : CurrentStage()

    object ENTERCLASS : CurrentStage()

    object ENTERDEPARTMENT : CurrentStage()

    object FINISH : CurrentStage()
}

object Dummy {
    val departments = listOf(
        "없음",
        "SW 개발과",
        "임베디드 개발과",
        "스마트IoT과",
        "임베디드SW과",
        "e-비즈니스과",
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val (bottomSheetVisible, setBottomSheetVisible) = remember {
        mutableStateOf(false)
    }

    val (department, onDepartmentValueChange) = remember {
        mutableStateOf("")
    }

    val (`class`, onClassValueChange) = remember {
        mutableStateOf("")
    }

    val (grade, onGradeValueChange) = remember {
        mutableStateOf("")
    }

    val (school, onSchoolValueChange) = remember {
        mutableStateOf("")
    }

    val (currentStage, setCurrentStage) = remember {
        mutableStateOf<CurrentStage>(CurrentStage.ENTERSCHOOL)
    }

    setCurrentStage(
        when {
            department.isBlank() && `class`.isBlank() && grade.isBlank() && school.isBlank() -> CurrentStage.ENTERSCHOOL
            department.isBlank() && `class`.isBlank() && grade.isBlank() -> CurrentStage.ENTERGRADE
            department.isBlank() && `class`.isBlank() -> CurrentStage.ENTERCLASS
            department.isBlank() -> CurrentStage.ENTERDEPARTMENT
            else -> CurrentStage.FINISH
        }
    )

    ONMITheme { color, typography ->
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color.BackgroundMain),
            topBar = {
                TopNavigationBar(
                    leading = {
                        WrappedIconButton(onClick = { /*onBackClick*/ }) {
                            ArrowBackIcon(tint = color.Black)
                        }
                    }
                )
            },
            bottomBar = {
                AnimatedVisibility(visible = currentStage == CurrentStage.ENTERDEPARTMENT || currentStage == CurrentStage.FINISH) {
                    ONMIButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(52.dp),
                        text = if (currentStage == CurrentStage.ENTERDEPARTMENT) "이대로하기" else "확인!",
                        isEnabled = true
                    ) {

                    }
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
                    visible = currentStage != CurrentStage.ENTERSCHOOL
                ) {
                    GreetingComponent(
                        greetings = when (currentStage) {
                            CurrentStage.ENTERSCHOOL -> Pair("", "")
                            CurrentStage.ENTERGRADE -> Pair("몇학년 이신가요?", "")
                            CurrentStage.ENTERCLASS -> Pair("몇반 이신가요?", "")
                            CurrentStage.ENTERDEPARTMENT -> Pair(
                                "특정 학과에 다니시나요?",
                                "학과는 없으면 안해도 괜찮아요!"
                            )

                            CurrentStage.FINISH -> Pair(
                                "입력하신 정보가 정확한가요?",
                                "정보는 설정에서 얼마든지 변경할 수 있어요."
                            )
                        },
                    )
                }
                AnimatedVisibility(
                    modifier = Modifier.padding(bottom = 24.dp),
                    visible = currentStage == CurrentStage.ENTERDEPARTMENT || currentStage == CurrentStage.FINISH
                ) {
                    LabelTextFiled(
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = "학과",
                        value = department,
                        onValueChange = onDepartmentValueChange,
                        placeHolderText = "학과를 선택해주세요.",
                        readOnly = true,
                        onClick = {
                            setBottomSheetVisible(true)
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
                    visible = currentStage == CurrentStage.ENTERCLASS || currentStage == CurrentStage.ENTERDEPARTMENT || currentStage == CurrentStage.FINISH
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
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Up)
                        })
                    )
                }
                AnimatedVisibility(
                    modifier = Modifier.padding(bottom = 24.dp),
                    visible = currentStage != CurrentStage.ENTERSCHOOL
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
                    onValueChange = onSchoolValueChange,
                    placeHolderText = "학교이름을 입력해주세요.",
                    onTrailingIconClick = {
                        onSchoolValueChange("")
                    },
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Up)
                    })
                )
            }

            if (bottomSheetVisible) {
                DepartmentSelectorBottomSheet(
                    departments = Dummy.departments,
                    sheetState = sheetState,
                    selectedItemIdx = Dummy.departments.indexOf(department),
                    onItemClick = { idx ->
                        onDepartmentValueChange(Dummy.departments[idx])
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                setBottomSheetVisible(false)
                                focusManager.clearFocus()
                            }
                        }
                    },
                    onDismissRequest = {
                        setBottomSheetVisible(false)
                        focusManager.clearFocus()
                    }
                )
            }
        }
    }
}