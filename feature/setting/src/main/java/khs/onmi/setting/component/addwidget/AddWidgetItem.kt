package khs.onmi.setting.component.addwidget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.R as DR

@Composable
fun AddWidgetItem(
    @DrawableRes previewImage: Int,
    widgetName: String,
    widgetSize: String,
    onClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(onClick = onClick)
                .background(
                    color = color.UnselectedSecondary,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(top = 24.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.shadow(
                    elevation = 16.dp,
                    spotColor = Color(0xFF2C2C2C),
                    ambientColor = Color(0xFF2C2C2C)
                ),
                imageVector = ImageVector.vectorResource(previewImage),
                contentDescription = "add widget item"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = widgetName,
                style = typography.Headline4,
                color = color.TextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = widgetSize,
                style = typography.Caption1,
                color = color.TextSecondary
            )
        }
    }
}

@Preview
@Composable
private fun AddWidgetItemPreview() {
    AddWidgetItem(
        previewImage = DR.drawable.combined_widget_preview,
        widgetName = "Widget Name",
        widgetSize = "Widget Size",
        onClick = {}
    )
}