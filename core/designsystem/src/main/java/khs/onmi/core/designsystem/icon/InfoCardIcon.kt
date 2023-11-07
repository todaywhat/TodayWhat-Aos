package khs.onmi.core.designsystem.icon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import khs.onmi.core.designsystem.R
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun InfoCardMealIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_info_meal),
        contentDescription = "Info Card Meal Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun InfoCardTimeTableIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_info_time_table),
        contentDescription = "Info Card Time Table Icon",
        tint = tint,
        modifier = modifier
    )
}

@Preview
@Composable
fun InfoCardIconsPre() {
    ONMITheme { color, _ ->
        Column {
            InfoCardMealIcon(tint = color.UnselectedSecondary)
            InfoCardTimeTableIcon(tint = color.UnselectedSecondary)
        }
    }
}