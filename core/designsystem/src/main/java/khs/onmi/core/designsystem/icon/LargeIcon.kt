package khs.onmi.core.designsystem.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import khs.onmi.core.designsystem.R
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun NotificationsIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_notifications),
        contentDescription = "Large Notifications Icon",
        modifier = modifier
    )
}

@Composable
fun NotificationsFillIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_notifications_fill),
        contentDescription = "Large Notifications Fill Icon",
        modifier = modifier
    )
}

@Composable
fun SettingIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_setting),
        contentDescription = "Large Setting Icon",
        modifier = modifier
    )
}

@Composable
fun AllergiesIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_large_allergies),
        contentDescription = "Large allergies Icon",
        modifier = modifier
    )
}

@Composable
fun GithubIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_github),
        contentDescription = "Large Github Icon",
        modifier = modifier
    )
}

@Composable
fun EmailIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_email),
        contentDescription = "Large Email Icon",
        modifier = modifier
    )
}

@Preview
@Composable
fun LargeIconsPre() {
    ONMITheme { color, _ ->
        Column(modifier = Modifier.background(color.AbsoluteWhite)) {
            NotificationsIcon()
            NotificationsFillIcon()
            SettingIcon()
            AllergiesIcon()
            GithubIcon()
            EmailIcon()
        }
    }
}