package khs.onmi.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.modifier.onmiClickable
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun TopNavigationBar(onBackButtonClick: () -> Unit) {
    ONMITheme { color, _ ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArrowBackIcon(
                tint = color.Black,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                    .onmiClickable(
                        onClick = onBackButtonClick,
                        bounded = false
                    )
            )
        }
    }
}

@Preview
@Composable
fun TopNavigationBarPre() {
    TopNavigationBar {

    }
}