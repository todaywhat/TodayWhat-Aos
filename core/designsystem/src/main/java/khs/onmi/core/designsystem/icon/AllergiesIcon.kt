package khs.onmi.core.designsystem.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.R
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
private fun BasicAllergiesIcon(
    id: Int,
    isItemSelected: Boolean,
) {
    ONMITheme { color, _ ->
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color.BackgroundSecondary)
        ) {
            Icon(
                painter = painterResource(id = id),
                contentDescription = "Allergies Icon",
                modifier = Modifier.align(Alignment.Center),
                tint = if (isItemSelected) color.Black else color.UnselectedPrimary
            )
        }
    }
}

@Composable
fun AllergiesEggIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_egg,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesMilkIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_milk,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesBuckwheatIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_buckwheat,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesPeanutIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_peanut,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesPeaIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_pea,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesWheatIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_wheat,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesMackerelIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_mackerel,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesCrabIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_crab,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesShrimpIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_shrimp,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesPorkIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_pork,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesPeachIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_peach,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesTomatoIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_tomato,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesSulphuricAcidIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_sulphuric_acid,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesWalnutIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_walnut,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesChickenIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_chicken,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesBeefIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_beef,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesSquidIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_squid,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesShellfishIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_shellfish,
        isItemSelected = isItemSelected
    )
}

@Composable
fun AllergiesPineNutIcon(isItemSelected: Boolean) {
    BasicAllergiesIcon(
        id = R.drawable.ic_allergies_pine_nut,
        isItemSelected = isItemSelected
    )
}

@Preview
@Composable
fun AllergyIconPre() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_egg, isItemSelected = true)
}