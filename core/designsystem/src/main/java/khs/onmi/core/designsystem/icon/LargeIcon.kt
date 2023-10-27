package khs.onmi.core.designsystem.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.R
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun BasicLargeIcon(
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

@Composable
fun NotificationsIcon(
    modifier: Modifier = Modifier,
) {
    BasicLargeIcon(
        id = R.drawable.ic_notifications,
        contentDescription = "Large Notifications Icon",
        modifier = modifier
    )
}

@Composable
fun NotificationsFillIcon(
    modifier: Modifier = Modifier,
) {
    ONMITheme { color, _ ->
        Box(modifier = Modifier.size(32.dp)) {
            BasicLargeIcon(
                id = R.drawable.ic_notifications_fill,
                contentDescription = "Large Notifications Fill Icon",
                modifier = modifier
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 4.5.dp, end = 7.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color.Point)
                        .align(Alignment.TopEnd)
                )
            }
        }
    }
}

@Composable
fun SettingIcon(
    modifier: Modifier = Modifier,
) {
    BasicLargeIcon(
        id = R.drawable.ic_setting,
        contentDescription = "Large Setting Icon",
        modifier = modifier
    )
}

@Composable
fun AllergiesIcon(
    modifier: Modifier = Modifier,
) {
    BasicLargeIcon(
        id = R.drawable.ic_large_allergies,
        contentDescription = "Large allergies Icon",
        modifier = modifier
    )
}

@Composable
fun GithubIcon(
    modifier: Modifier = Modifier,
) {
    BasicLargeIcon(
        id = R.drawable.ic_github,
        contentDescription = "Large Github Icon",
        modifier = modifier
    )
}

@Composable
fun EmailIcon(
    modifier: Modifier = Modifier,
) {
    BasicLargeIcon(
        id = R.drawable.ic_email,
        contentDescription = "Large Email Icon",
        modifier = modifier
    )
}

@Preview
@Composable
fun LargeIconsPre() {
    ONMITheme { color, _ ->
        Column(modifier = Modifier.background(color.White)) {
            NotificationsIcon()
            NotificationsFillIcon()
            SettingIcon()
            AllergiesIcon()
            GithubIcon()
            EmailIcon()
        }
    }
}