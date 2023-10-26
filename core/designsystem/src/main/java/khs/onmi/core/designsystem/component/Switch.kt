package khs.onmi.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun ONMISwitch(
    isChecked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
) {
    ONMITheme { color, _ ->
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = color.AbsoluteWhite,
                uncheckedThumbColor = color.AbsoluteWhite,
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent,
                checkedTrackColor = color.TextPrimary,
                uncheckedTrackColor = color.UnselectedSecondary
            ),
            thumbContent = {
                Box(
                    modifier = Modifier
                        .size(23.dp)
                        .clip(CircleShape)
                        .background(color.AbsoluteWhite)
                )
            }
        )
    }
}

@Preview
@Composable
fun ONMISwitchPre() {
    var isChecked by remember {
        mutableStateOf(false)
    }

    ONMISwitch(isChecked = isChecked) { checked ->
        isChecked = checked
    }
}