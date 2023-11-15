package khs.onmi.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.modifier.onmiClickable
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun TopNavigationBar(
    insideLeftIcon: (@Composable () -> Unit)? = null,
    onInSideLeftIconClick: (() -> Unit)? = null,
) {
    ONMITheme { color, _ ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            insideLeftIcon?.let {
                IconButton(onClick = onInSideLeftIconClick!!) {
                    insideLeftIcon()
                }
            }
        }
    }
}

@Preview
@Composable
fun TopNavigationBarPre() {
    TopNavigationBar(
        insideLeftIcon = {
            ArrowBackIcon(tint = Color.Black)
        },
        onInSideLeftIconClick = {

        }
    )
}