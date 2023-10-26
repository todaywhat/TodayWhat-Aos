package khs.onmi.core.designsystem.component

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun ONMIRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
) {
    ONMITheme { color, _ ->
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = color.TextPrimary,
                unselectedColor = color.UnselectedPrimary
            )
        )
    }
}

@Preview
@Composable
fun ONMIRadioButtonPre() {
    var selected by remember {
        mutableStateOf(false)
    }

    ONMIRadioButton(selected = selected) {
        selected = !selected
    }
}