package khs.onmi.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ColumnSpacer
import khs.onmi.core.designsystem.component.ONMITextField
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun LabelTextFiled(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    onTrailingIconClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        Column {
            Text(
                text = label,
                style = typography.Body2,
                color = color.TextPrimary
            )
            ColumnSpacer(dp = 8.dp)
            ONMITextField(
                modifier = modifier,
                value = value,
                placeHolderText = placeHolderText,
                onValueChange = onValueChange,
                onTrailingIconClick = onTrailingIconClick
            )
        }
    }
}