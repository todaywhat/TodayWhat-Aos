package khs.onmi.enterinformation.component.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import khs.onmi.core.ui.LabelTextFiled

@Composable
fun DepartmentInput(
    visible: Boolean,
    value: String,
    onClick: () -> Unit,
    onClear: () -> Unit,
    onDone: KeyboardActionScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { -it },
        exit = fadeOut()
    ) {
        LabelTextFiled(
            modifier = Modifier.fillMaxWidth(),
            label = "학과",
            value = value,
            placeHolderText = "학과를 선택해주세요.",
            isReadOnly = true,
            onClick = onClick,
            onTrailingIconClick = onClear,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(onDone = onDone)
        )
    }
}