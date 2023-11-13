package khs.onmi.core.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnSpacer(dp: Dp) {
    Spacer(modifier = Modifier.height(dp))
}

@Composable
fun ColumnSpacer(percent: Float) {
    Spacer(modifier = Modifier.fillMaxHeight(percent))
}

@Composable
fun RowSpacer(dp: Dp) {
    Spacer(modifier = Modifier.height(dp))
}

@Composable
fun RowSpacer(percent: Float) {
    Spacer(modifier = Modifier.fillMaxWidth(percent))
}