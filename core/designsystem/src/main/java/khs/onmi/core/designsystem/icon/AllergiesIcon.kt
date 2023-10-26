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
private fun BasicAllergiesIcon(id: Int) {
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
                tint = color.UnselectedPrimary
            )
        }
    }
}

@Preview
@Composable
fun AllergiesEggIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_egg)
}

@Preview
@Composable
fun AllergiesMilkIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_milk)
}

@Preview
@Composable
fun AllergiesBuckwheatIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_buckwheat)
}

@Preview
@Composable
fun AllergiesPeanutIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_peanut)
}

@Preview
@Composable
fun AllergiesPeaIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_pea)
}

@Preview
@Composable
fun AllergiesWheatIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_wheat)
}

@Preview
@Composable
fun AllergiesMackerelIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_mackerel)
}

@Preview
@Composable
fun AllergiesCrabIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_crab)
}

@Preview
@Composable
fun AllergiesShrimpIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_shrimp)
}

@Preview
@Composable
fun AllergiesPorkIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_pork)
}

@Preview
@Composable
fun AllergiesPeachIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_peach)
}

@Preview
@Composable
fun AllergiesTomatoIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_tomato)
}

@Preview
@Composable
fun AllergiesSulphuricAcidIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_sulphuric_acid)
}

@Preview
@Composable
fun AllergiesWalnutIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_walnut)
}

@Preview
@Composable
fun AllergiesChickenIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_chicken)
}

@Preview
@Composable
fun AllergiesBeefIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_beef)
}

@Preview
@Composable
fun AllergiesSquidIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_squid)
}

@Preview
@Composable
fun AllergiesShellfishIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_shellfish)
}

@Preview
@Composable
fun AllergiesPineNutIcon() {
    BasicAllergiesIcon(id = R.drawable.ic_allergies_pine_nut)
}