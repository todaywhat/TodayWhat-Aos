package khs.onmi.core.designsystem.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun BasicIcon(
    id: Int,
    contentDescription: String,
    modifier: Modifier,
) {
    ONMITheme { color, _ ->
        Icon(
            painter = painterResource(id = id),
            contentDescription = contentDescription,
            tint = color.Black,
            modifier = modifier
        )
    }
}