package khs.onmi.core.designsystem.icon

import androidx.compose.foundation.background
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
fun SchoolIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_school),
        contentDescription = "School Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun AllergiesIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_allergies),
        contentDescription = "Allergies Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun FeedbackIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_feedback),
        contentDescription = "Feedback Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun ModifyIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_modify),
        contentDescription = "Modify Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun RiceIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_rice),
        contentDescription = "Rice Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun ClockIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_clock),
        contentDescription = "Clock Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun CalendarIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_calendar),
        contentDescription = "Calendar Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun TrashCanIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_trash_can),
        contentDescription = "Trash Can Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun BookIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_book),
        contentDescription = "Book Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun DownIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_down),
        contentDescription = "Down Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun RightArrowIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_right_arrow),
        contentDescription = "Right Arrow Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun XMarkCircleFillIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_xmark_circle_fill),
        contentDescription = "XMark Circle Fill Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun ArrowBackIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_back),
        contentDescription = "Arrow Back Icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun PaperIcon(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_paper),
        contentDescription = "Paper Icon",
        tint = tint,
        modifier = modifier
    )
}

@Preview
@Composable
fun IconsPre() {
    ONMITheme { color, _ ->
        Column(modifier = Modifier.background(color.White)) {
            SchoolIcon(tint = color.TextPrimary)
            AllergiesIcon(tint = color.TextPrimary)
            FeedbackIcon(tint = color.TextPrimary)
            ModifyIcon(tint = color.TextPrimary)
            RiceIcon(tint = color.TextPrimary)
            ClockIcon(tint = color.TextPrimary)
            CalendarIcon(tint = color.TextPrimary)
            TrashCanIcon(tint = color.TextPrimary)
            BookIcon(tint = color.TextPrimary)
            DownIcon(tint = color.TextPrimary)
            RightArrowIcon(tint = color.TextPrimary)
            XMarkCircleFillIcon(tint = color.TextPrimary)
            ArrowBackIcon(tint = color.TextPrimary)
            PaperIcon(tint = color.TextPrimary)
        }
    }
}