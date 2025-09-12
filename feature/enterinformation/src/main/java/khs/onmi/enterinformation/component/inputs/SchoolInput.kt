package khs.onmi.enterinformation.component.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ColumnSpacer
import khs.onmi.core.ui.LabelTextFiled
import khs.onmi.enterinformation.component.SchoolSelector

@Composable
fun SchoolInput(
    selectorVisible: Boolean,
    schools: List<Pair<String, String>>,
    value: String,
    onSchoolValueChange: (grade: String) -> Unit,
    onClick: () -> Unit,
    onNext: KeyboardActionScope.() -> Unit,
    onSchoolSelected: (idx: Int) -> Unit,
) {
    LabelTextFiled(
        modifier = Modifier.fillMaxWidth(),
        label = "학교이름",
        value = value,
        placeHolderText = "학교이름을 입력해주세요.",
        onValueChange = onSchoolValueChange,
        onClick = onClick,
        onTrailingIconClick = {
            onSchoolValueChange("")
        },
        imeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = onNext)
    )
    AnimatedVisibility(visible = selectorVisible) {
        ColumnSpacer(dp = 8.dp)
        SchoolSelector(
            schools = schools,
            onItemClick = onSchoolSelected
        )
    }
}