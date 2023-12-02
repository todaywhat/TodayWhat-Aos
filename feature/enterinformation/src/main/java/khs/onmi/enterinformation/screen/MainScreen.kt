package khs.onmi.enterinformation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import khs.onmi.core.designsystem.component.ONMIButton
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton
import khs.onmi.core.ui.LabelTextFiled
import khs.onmi.enterinformation.component.GreetingComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController
) {
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
                ONMIButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(52.dp),
                    text = "확인!",
                    isEnabled = department.isNotBlank() && `class`.isNotBlank() && grade.isNotBlank() && school.isNotBlank()
                ) {

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
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                GreetingComponent()
                LabelTextFiled(
                    modifier = Modifier.fillMaxWidth(),
                    label = "학과",
                    value = department,
                    onValueChange = onDepartmentValueChange,
                    placeHolderText = "학과를 선택해주세요.",
                    onTrailingIconClick = {
                        onDepartmentValueChange("")
                    }
                )
                LabelTextFiled(
                    modifier = Modifier.fillMaxWidth(),
                    label = "반",
                    value = `class`,
                    onValueChange = onClassValueChange,
                    placeHolderText = "반을 입력해주세요.",
                    onTrailingIconClick = {
                        onClassValueChange("")
                    }
                )
                LabelTextFiled(
                    modifier = Modifier.fillMaxWidth(),
                    label = "학년",
                    value = grade,
                    onValueChange = onGradeValueChange,
                    placeHolderText = "학년을 입력해주세요.",
                    onTrailingIconClick = {
                        onGradeValueChange("")
                    }
                )
                LabelTextFiled(
                    modifier = Modifier.fillMaxWidth(),
                    label = "학교이름",
                    value = school,
                    onValueChange = onSchoolValueChange,
                    placeHolderText = "학교이름을 입력해주세요.",
                    onTrailingIconClick = {
                        onSchoolValueChange("")
                    }
                )
            }
        }
    }
}