package khs.onmi.setting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.RiceIcon
import khs.onmi.core.designsystem.theme.ONMITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToggleItem(
    icon: @Composable () -> Unit,
    title: String,
    value: Boolean,
    onValueChange: (value: Boolean) -> Unit
) {
    ONMITheme { color, typography ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon()
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = typography.Body3,
                color = color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                Switch(
                    checked = value,
                    onCheckedChange = onValueChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = color.White,
                        uncheckedThumbColor = color.White,
                        checkedTrackColor = color.Black,
                        uncheckedTrackColor = color.UnselectedSecondary,
                        uncheckedBorderColor = color.UnselectedSecondary
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun ToggleItemPre() {
    val (value, onValueChange) = remember { mutableStateOf(false) }

    ONMITheme { color, _ ->
        ToggleItem(
            icon = { RiceIcon(tint = color.Black) },
            title = "저녁 후 내일 급식 표시",
            value = value,
            onValueChange = onValueChange
        )
    }
}