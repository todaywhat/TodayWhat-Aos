package khs.onmi.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.NotificationsIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton

@Composable
fun TopNavigationBar(
    title: String = "",
    leading: @Composable RowScope.() -> Unit = {},
    trailing: @Composable RowScope.() -> Unit = {},
) {
    ONMITheme { color, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = leading
            )
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                style = typography.Headline4,
                color = color.Black
            )
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = trailing
            )
        }
    }
}

@Preview
@Composable
fun TopNavigationBarPre() {
    ONMITheme { color, _ ->
        TopNavigationBar(
            title = "오늘뭐임탑바",
            leading = {
                WrappedIconButton(onClick = { /*TODO*/ }) {
                    NotificationsIcon(tint = color.Black)
                }
                WrappedIconButton(onClick = { /*TODO*/ }) {
                    NotificationsIcon(tint = color.Black)
                }
            },
            trailing = {
                WrappedIconButton(onClick = { /*TODO*/ }) {
                    NotificationsIcon(tint = color.Black)
                }
                WrappedIconButton(onClick = { /*TODO*/ }) {
                    NotificationsIcon(tint = color.Black)
                }
            },
        )
    }
}