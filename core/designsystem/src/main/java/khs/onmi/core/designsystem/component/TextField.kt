package khs.onmi.core.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.XMarkCircleFillIcon
import khs.onmi.core.designsystem.theme.ONMITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ONMITextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
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
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            value = value,
            onValueChange = onValueChange,
            textStyle = typography.Body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = color.TextPrimary,
                cursorColor = color.TextPrimary,
                containerColor = color.CardBackground,
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
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun ONMITextFieldPre() {
    val kc = LocalSoftwareKeyboardController.current
    var text by remember {
        mutableStateOf("")
    }

    ONMITextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        placeHolderText = "ONMITextField",
        onValueChange = { text = it },
        onTrailingIconClick = { text = "" }
    )
}