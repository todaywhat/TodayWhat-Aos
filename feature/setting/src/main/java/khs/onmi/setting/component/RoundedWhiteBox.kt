package khs.onmi.setting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun RoundedWhiteBox(content: @Composable BoxScope.() -> Unit) {
    ONMITheme { color, _ ->
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color.CardBackgroundSecondary)
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .wrapContentSize(),
            content = content
        )
    }
}