package khs.onmi.allergies.constant

import androidx.annotation.DrawableRes
import khs.onmi.core.designsystem.R as DR

internal data class Allergy(
    val id: Int,
    val name: String,
    @get:DrawableRes val iconId: Int,
)

internal object AllergyConstants {
    val entries = listOf(
        Allergy(1, "난류", DR.drawable.ic_allergies_egg),
        Allergy(2, "우유", DR.drawable.ic_allergies_milk),
        Allergy(3, "메밀", DR.drawable.ic_allergies_buckwheat),
        Allergy(4, "땅콩", DR.drawable.ic_allergies_peanut),
        Allergy(5, "대두", DR.drawable.ic_allergies_pea),
        Allergy(6, "밀", DR.drawable.ic_allergies_wheat),
        Allergy(7, "고등어", DR.drawable.ic_allergies_mackerel),
        Allergy(8, "게", DR.drawable.ic_allergies_crab),
        Allergy(9, "새우", DR.drawable.ic_allergies_shrimp),
        Allergy(10, "돼지고기", DR.drawable.ic_allergies_pork),
        Allergy(11, "복숭아", DR.drawable.ic_allergies_peach),
        Allergy(12, "토마토", DR.drawable.ic_allergies_tomato),
        Allergy(13, "아황산류", DR.drawable.ic_allergies_sulphuric_acid),
        Allergy(14, "호두", DR.drawable.ic_allergies_walnut),
        Allergy(15, "닭고기", DR.drawable.ic_allergies_chicken),
        Allergy(16, "소고기", DR.drawable.ic_allergies_beef),
        Allergy(17, "오징어", DR.drawable.ic_allergies_squid),
        Allergy(18, "조개", DR.drawable.ic_allergies_shellfish),
        Allergy(19, "잣", DR.drawable.ic_allergies_pine_nut),
    )
}