package com.onmi.widget.glance.meal

import kotlinx.serialization.Serializable


@Serializable
sealed interface MealInfo {
    @Serializable
    object Loading : MealInfo

    @Serializable
    data class Available(
        val time: String,
        val mealList: List<String>,
    ) : MealInfo

    @Serializable
    object Unavailable : MealInfo
}