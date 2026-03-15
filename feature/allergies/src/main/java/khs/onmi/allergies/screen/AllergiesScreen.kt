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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.allergies.viewmodel.container.AllergiesState
import khs.onmi.core.designsystem.component.AllergiesCard
import khs.onmi.core.designsystem.component.TopNavigationBar
import khs.onmi.core.designsystem.icon.AllergiesBeefIcon
import khs.onmi.core.designsystem.icon.AllergiesBuckwheatIcon
import khs.onmi.core.designsystem.icon.AllergiesChickenIcon
import khs.onmi.core.designsystem.icon.AllergiesCrabIcon
import khs.onmi.core.designsystem.icon.AllergiesEggIcon
import khs.onmi.core.designsystem.icon.AllergiesMackerelIcon
import khs.onmi.core.designsystem.icon.AllergiesMilkIcon
import khs.onmi.core.designsystem.icon.AllergiesPeaIcon
import khs.onmi.core.designsystem.icon.AllergiesPeachIcon
import khs.onmi.core.designsystem.icon.AllergiesPeanutIcon
import khs.onmi.core.designsystem.icon.AllergiesPineNutIcon
import khs.onmi.core.designsystem.icon.AllergiesPorkIcon
import khs.onmi.core.designsystem.icon.AllergiesShellfishIcon
import khs.onmi.core.designsystem.icon.AllergiesShrimpIcon
import khs.onmi.core.designsystem.icon.AllergiesSquidIcon
import khs.onmi.core.designsystem.icon.AllergiesSulphuricAcidIcon
import khs.onmi.core.designsystem.icon.AllergiesTomatoIcon
import khs.onmi.core.designsystem.icon.AllergiesWalnutIcon
import khs.onmi.core.designsystem.icon.AllergiesWheatIcon
import khs.onmi.core.designsystem.icon.ArrowBackIcon
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.core.designsystem.utils.WrappedIconButton

private data class AllergyItem(
    val name: String,
    val icon: @Composable (isSelected: Boolean) -> Unit,
)

@Composable
fun AllergiesScreen(
    uiState: AllergiesState,
    onBackPressed: () -> Unit,
    onAllergyToggle: (index: Int) -> Unit,
) {
    val allergyItems = remember {
        listOf(
            AllergyItem("난류") { AllergiesEggIcon(isItemSelected = it) },
            AllergyItem("우유") { AllergiesMilkIcon(isItemSelected = it) },
            AllergyItem("메밀") { AllergiesBuckwheatIcon(isItemSelected = it) },
            AllergyItem("땅콩") { AllergiesPeanutIcon(isItemSelected = it) },
            AllergyItem("대두") { AllergiesPeaIcon(isItemSelected = it) },
            AllergyItem("밀") { AllergiesWheatIcon(isItemSelected = it) },
            AllergyItem("고등어") { AllergiesMackerelIcon(isItemSelected = it) },
            AllergyItem("게") { AllergiesCrabIcon(isItemSelected = it) },
            AllergyItem("새우") { AllergiesShrimpIcon(isItemSelected = it) },
            AllergyItem("돼지고기") { AllergiesPorkIcon(isItemSelected = it) },
            AllergyItem("복숭아") { AllergiesPeachIcon(isItemSelected = it) },
            AllergyItem("토마토") { AllergiesTomatoIcon(isItemSelected = it) },
            AllergyItem("아황산류") { AllergiesSulphuricAcidIcon(isItemSelected = it) },
            AllergyItem("호두") { AllergiesWalnutIcon(isItemSelected = it) },
            AllergyItem("닭고기") { AllergiesChickenIcon(isItemSelected = it) },
            AllergyItem("소고기") { AllergiesBeefIcon(isItemSelected = it) },
            AllergyItem("오징어") { AllergiesSquidIcon(isItemSelected = it) },
            AllergyItem("조개") { AllergiesShellfishIcon(isItemSelected = it) },
            AllergyItem("잣") { AllergiesPineNutIcon(isItemSelected = it) },
        )
    }

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
                    itemsIndexed(allergyItems) { index, item ->
                        val isSelected = uiState.selectedAllergies.contains(index)
                        AllergiesCard(
                            modifier = Modifier.height(96.dp),
                            isSelected = isSelected,
                            itemNumber = index + 1,
                            allergyName = item.name,
                            allergyIcon = { item.icon(isSelected) },
                            onItemClick = { onAllergyToggle(index) }
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
        uiState = AllergiesState(selectedAllergies = setOf(0, 3, 7)),
        onBackPressed = {},
        onAllergyToggle = {},
    )
}
