package khs.onmi.enterinformation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ColumnSpacer
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun GreetingComponent() {
    ONMITheme { color, typography ->
        Column {
            Text(
                text = "입력하신 정보가 정확한가요?",
                style = typography.Headline2,
                color = color.Black
            )
            ColumnSpacer(dp = 8.dp)
            Text(
                text = "정보는 설정에서 언제든지 변경할 수 있어요.",
                style = typography.Body3,
                color = color.Black
            )
        }
    }
}