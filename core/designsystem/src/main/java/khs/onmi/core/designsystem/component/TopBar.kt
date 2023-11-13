package khs.onmi.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
    onInSideLeftIconClick: (() -> Unit)? = null,
    onOutSideLeftIconClick: (() -> Unit)? = null,
    onInSideRightIconClick: (() -> Unit)? = null,
    onOutSideRightIconClick: (() -> Unit)? = null,
    outsideLeftIcon: (@Composable () -> Unit)? = null,
    insideLeftIcon: (@Composable () -> Unit)? = null,
    outsideRightIcon: (@Composable () -> Unit)? = null,
    insideRightIcon: (@Composable () -> Unit)? = null,
) {
    ONMITheme { color, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WrappedIconButton(onClick = onOutSideLeftIconClick, icon = outsideLeftIcon)
                RowSpacer(dp = 16.dp)
                WrappedIconButton(onClick = onInSideLeftIconClick, icon = insideLeftIcon)
            }
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                style = typography.Headline4,
                color = color.Black
            )
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WrappedIconButton(onClick = onInSideRightIconClick, icon = insideRightIcon)
                RowSpacer(dp = 16.dp)
                WrappedIconButton(onClick = onOutSideRightIconClick, icon = outsideRightIcon)
            }
        }
    }
}

@Preview
@Composable
fun TopNavigationBarPre() {
    ONMITheme { color, _ ->
        TopNavigationBar(
            title = "오늘뭐임탑바",
            outsideLeftIcon = {
                NotificationsIcon(tint = color.Black)
            },
            insideLeftIcon = {
                NotificationsIcon(tint = color.Black)
            },
            outsideRightIcon = {
                NotificationsIcon(tint = color.Black)
            },
            insideRightIcon = {
                NotificationsIcon(tint = color.Black)
            },
            onOutSideLeftIconClick = {},
            onInSideLeftIconClick = {},
            onOutSideRightIconClick = {},
            onInSideRightIconClick = {}
        )
    }
}