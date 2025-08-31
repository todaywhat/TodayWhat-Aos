package khs.onmi.enterinformation.component.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import khs.onmi.core.ui.LabelTextFiled

@Composable
fun GradeInput(
    visible: Boolean,
    value: String,
    onGradeValueChange: (grade: String) -> Unit,
    onNext: KeyboardActionScope.() -> Unit,
) {
    AnimatedVisibility(visible = visible) {
        LabelTextFiled(
            modifier = Modifier.fillMaxWidth(),
            label = "학년",
            value = value,
            onValueChange = onGradeValueChange,
            placeHolderText = "학년을 입력해주세요.",
            onTrailingIconClick = { onGradeValueChange("") },
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(onNext = onNext)
        )
    }
}