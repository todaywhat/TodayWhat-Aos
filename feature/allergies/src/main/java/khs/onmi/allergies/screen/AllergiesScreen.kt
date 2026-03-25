package khs.onmi.allergies.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.allergies.constant.AllergyConstants
import khs.onmi.allergies.viewmodel.container.AllergiesState
import khs.onmi.core.designsystem.component.AllergiesCard
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton

@Composable
fun AllergiesScreen(
    uiState: AllergiesState,
    onBackPressed: () -> Unit,
    onAllergyToggle: (id: Int) -> Unit,
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
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "알레르기",
                    style = typography.Headline1,
                    color = color.Black
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(AllergyConstants.entries) { allergy ->
                        AllergiesCard(
                            modifier = Modifier.height(96.dp),
                            id = allergy.id,
                            name = allergy.name,
                            iconId = allergy.iconId,
                            isSelected = uiState.selectedAllergyIds.contains(allergy.id),
                            onItemClick = { onAllergyToggle(allergy.id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllergiesScreenPreview() {
    AllergiesScreen(
        uiState = AllergiesState(),
        onBackPressed = {},
        onAllergyToggle = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun AllergiesScreenSelectedPreview() {
    AllergiesScreen(
        uiState = AllergiesState(selectedAllergyIds = setOf(1, 3, 7)),
        onBackPressed = {},
        onAllergyToggle = {},
    )
}
