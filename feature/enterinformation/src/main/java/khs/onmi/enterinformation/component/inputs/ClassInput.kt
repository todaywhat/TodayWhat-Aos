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
import androidx.compose.ui.text.input.KeyboardType
import khs.onmi.core.ui.LabelTextFiled

@Composable
fun ClassInput(
    visible: Boolean,
    value: String,
    onClassValueChange: (`class`: String) -> Unit,
    onNext: KeyboardActionScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { -it },
        exit = fadeOut()
    ) {
        LabelTextFiled(
            modifier = Modifier.fillMaxWidth(),
            label = "반",
            value = value,
            onValueChange = onClassValueChange,
            placeHolderText = "반을 입력해주세요.",
            onTrailingIconClick = { onClassValueChange("") },
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.NumberPassword,
            keyboardActions = KeyboardActions(onNext = onNext)
        )
    }
}