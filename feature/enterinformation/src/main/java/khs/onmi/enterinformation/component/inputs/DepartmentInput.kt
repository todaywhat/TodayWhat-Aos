package khs.onmi.enterinformation.component.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ColumnSpacer
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
        enter = expandVertically(expandFrom = Alignment.Top) + fadeIn(),
        exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
    ) {
        Column {
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
            ColumnSpacer(dp = 24.dp)
        }
    }
}