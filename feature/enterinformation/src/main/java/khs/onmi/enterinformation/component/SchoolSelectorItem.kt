package khs.onmi.enterinformation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ColumnSpacer
import khs.onmi.core.designsystem.modifier.onmiClickable
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun SchoolSelectorItem(
    school: String,
    location: String,
    onClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onmiClickable(onClick = onClick)
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = school,
                    style = typography.Body3,
                    color = color.TextPrimary
                )
                ColumnSpacer(dp = 8.dp)
                Text(
                    text = location,
                    style = typography.Caption1,
                    color = color.UnselectedPrimary
                )
            }
            Divider(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                color = color.UnselectedSecondary,
                thickness = 1.dp
            )
        }
    }
}

@Preview
@Composable
fun SchoolSelectorItemPre() {
    ONMITheme { color, _ ->
        Column(modifier = Modifier.background(color.White)) {
            SchoolSelectorItem(
                school = "광주소프트웨어마이스터고등학교",
                location = "광주광역시 광산구",
                onClick = {}
            )
        }
    }
}
