package khs.onmi.allergies.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khs.onmi.allergies.viewmodel.container.AllergiesState
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton

@Composable
fun AllergiesScreen(
    uiState: AllergiesState,
    onBackPressed: () -> Unit,
) {
    ONMITheme { color, typography ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color.BackgroundSecondary)
                .safeDrawingPadding()
        ) {
            TopNavigationBar(
                leading = {
                    WrappedIconButton(onClick = onBackPressed) {
                        ArrowBackIcon(tint = color.Black)
                    }
                }
            )
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "알레르기 설정",
                    style = typography.Headline1,
                    color = color.Black
                )
            }
        }
    }
}
