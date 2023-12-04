package khs.onmi.enterinformation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ONMIRadioButton
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun DepartmentSelectorItem(
    department: String,
    isSelected: Boolean,
    onItemClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                text = department,
                style = typography.Headline4,
                color = color.TextPrimary
            )
            ONMIRadioButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                isSelected = isSelected,
                onClick = onItemClick
            )
        }
    }
}