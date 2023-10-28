package khs.onmi.core.designsystem.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import khs.onmi.core.designsystem.R
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun SchoolIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_school,
        contentDescription = "School Icon",
        modifier = modifier
    )
}

@Composable
fun AllergiesIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_allergies,
        contentDescription = "Allergies Icon",
        modifier = modifier
    )
}

@Composable
fun FeedbackIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_feedback,
        contentDescription = "Feedback Icon",
        modifier = modifier
    )
}

@Composable
fun ModifyIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_modify,
        contentDescription = "Modify Icon",
        modifier = modifier
    )
}

@Composable
fun RiceIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_rice,
        contentDescription = "Rice Icon",
        modifier = modifier
    )
}

@Composable
fun ClockIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_clock,
        contentDescription = "Clock Icon",
        modifier = modifier
    )
}

@Composable
fun CalendarIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_calendar,
        contentDescription = "Calendar Icon",
        modifier = modifier
    )
}

@Composable
fun TrashCanIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_trash_can,
        contentDescription = "Trash Can Icon",
        modifier = modifier
    )
}

@Composable
fun BookIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_book,
        contentDescription = "Book Icon",
        modifier = modifier
    )
}

@Composable
fun DownIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_down,
        contentDescription = "Down Icon",
        modifier = modifier
    )
}

@Composable
fun RightArrowIcon(
    modifier: Modifier = Modifier,
) {
    BasicIcon(
        id = R.drawable.ic_right_arrow,
        contentDescription = "Right Arrow Icon",
        modifier = modifier
    )
}

@Composable
fun XMarkCircleFillIcon(
    modifier: Modifier = Modifier,
) {
    ONMITheme { color, _ ->
        Icon(
            painter = painterResource(id = R.drawable.ic_xmark_circle_fill),
            contentDescription = "XMark Circle Fill Icon",
            tint = color.UnselectedPrimary,
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun IconsPre() {
    ONMITheme { color, _ ->
        Column(modifier = Modifier.background(color.White)) {
            SchoolIcon()
            AllergiesIcon()
            FeedbackIcon()
            ModifyIcon()
            RiceIcon()
            ClockIcon()
            CalendarIcon()
            TrashCanIcon()
            BookIcon()
            DownIcon()
            RightArrowIcon()
            XMarkCircleFillIcon()
        }
    }
}