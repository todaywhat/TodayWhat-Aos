package khs.onmi.setup.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.setup.model.SettingItemsData

@Composable
fun SettingListComponent(
    items: List<SettingItemsData>,
) {
    ONMITheme { color, typography ->
        LazyColumn(
            userScrollEnabled = false,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            itemsIndexed(items) { _, items ->
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = items.onClick,
                        )
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.CenterStart),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items.leading()
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = items.text,
                            style = typography.Body3,
                            color = color.Black
                        )
                    }
                    Box(modifier = Modifier.align(Alignment.CenterEnd), content = items.trailing)
                }
            }
        }
    }
}