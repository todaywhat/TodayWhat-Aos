package khs.onmi.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.ArrowUpRightIcon
import khs.onmi.core.designsystem.modifier.onmiClickable
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun ReviewNudge(
    text: String,
    onClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(color.Black)
                .onmiClickable(onClick = onClick)
                .padding(vertical = 14.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = typography.Body2,
                color = color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            ArrowUpRightIcon(tint = color.White)
        }
    }
}

@Preview
@Composable
fun ReviewNudgePreview() {
    ReviewNudge(
        text = "더 나은 급식, 시간표를 위해 리뷰를 남겨주세요",
        onClick = {}
    )
}