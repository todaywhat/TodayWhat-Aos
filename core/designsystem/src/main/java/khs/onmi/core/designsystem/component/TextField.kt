package khs.onmi.core.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.XMarkCircleFillIcon
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun ONMITextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isReadOnly: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    onClick: () -> Unit = {},
    onValueChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
) {
    var isFocused by remember {
        mutableStateOf(false)
    }

    ONMITheme { color, typography ->
        OutlinedTextField(
            modifier = modifier
                .height(52.dp)
                .border(
                    1.dp,
                    if (isFocused) color.Black else Color.Transparent,
                    RoundedCornerShape(8.dp)
                )
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    if (it.isFocused) {
                        onClick()
                    }
                },
            value = value,
            onValueChange = onValueChange,
            textStyle = typography.Body1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = color.TextPrimary,
                unfocusedTextColor = color.TextPrimary,
                cursorColor = color.TextPrimary,
                focusedContainerColor = color.TextPrimary,
                unfocusedContainerColor = color.TextPrimary,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    text = placeHolderText,
                    style = typography.Body1,
                    color = color.UnselectedPrimary
                )
            },
            trailingIcon = {
                if (value != "") {
                    IconButton(onClick = onTrailingIconClick) {
                        XMarkCircleFillIcon(tint = color.UnselectedPrimary)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            readOnly = isReadOnly,
        )
    }
}

@Preview
@Composable
fun ONMITextFieldPre() {
    val focusManager = LocalFocusManager.current
    var text by remember {
        mutableStateOf("")
    }
    var text2 by remember {
        mutableStateOf("")
    }
    var text3 by remember {
        mutableStateOf("")
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        ONMITextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            placeHolderText = "ONMITextField",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            onValueChange = { text = it },
            onTrailingIconClick = { text = "" }
        )
        ONMITextField(
            modifier = Modifier.fillMaxWidth(),
            value = text2,
            placeHolderText = "ONMITextField",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            onValueChange = { text2 = it },
            onTrailingIconClick = { text2 = "" }
        )
        ONMITextField(
            modifier = Modifier.fillMaxWidth(),
            value = text3,
            placeHolderText = "ONMITextField",
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            onValueChange = { text3 = it },
            onTrailingIconClick = { text3 = "" }
        )
    }
}