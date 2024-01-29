package khs.onmi.setup.model

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable

data class SettingItemsData(
    val leading:  @Composable () -> Unit,
    val text: String,
    val trailing: @Composable BoxScope.() -> Unit,
    val onClick: () -> Unit,
)
