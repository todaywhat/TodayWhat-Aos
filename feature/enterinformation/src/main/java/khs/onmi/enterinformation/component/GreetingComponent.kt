package khs.onmi.enterinformation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ColumnSpacer
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun GreetingComponent(
    greetings: Pair<String, String>
) {
    ONMITheme { color, typography ->
        Column {
            Text(
                text = greetings.first,
                style = typography.Headline2,
                color = color.Black
            )
            ColumnSpacer(dp = 8.dp)
            Text(
                text = greetings.second,
                style = typography.Body3,
                color = color.Black
            )
        }
    }
}