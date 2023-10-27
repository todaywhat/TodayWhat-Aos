package khs.onmi.core.designsystem.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import khs.onmi.core.designsystem.R

@Composable
fun CheckIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_check),
        contentDescription = "Toast Check Icon",
        modifier = modifier
    )
}

@Composable
fun XMarkIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_xmark),
        contentDescription = "Toast XMark Icon",
        modifier = modifier
    )
}

@Preview
@Composable
fun ToastIconsPre() {
    Column {
        CheckIcon()
        XMarkIcon()
    }
}