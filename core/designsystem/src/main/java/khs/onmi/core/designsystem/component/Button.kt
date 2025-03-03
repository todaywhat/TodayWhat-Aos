package khs.onmi.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun ONMIButton(
    text: String,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    colors: ButtonColors? = null,
    onClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        val buttonColors = colors
            ?: ButtonDefaults.buttonColors(
                contentColor = color.White,
                disabledContentColor = color.UnselectedPrimary,
                containerColor = color.Black,
                disabledContainerColor = color.UnselectedSecondary
            )

        Button(
            modifier = modifier,
            colors = buttonColors,
            shape = RoundedCornerShape(8.dp),
            onClick = onClick,
            enabled = isEnabled
        ) {
            Text(
                text = text,
                style = typography.Headline4
            )
        }
    }
}

@Preview
@Composable
fun ONMIButtonPre() {
    ONMIButton(
        text = "Text",
        isEnabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp)
    ) {

    }
}