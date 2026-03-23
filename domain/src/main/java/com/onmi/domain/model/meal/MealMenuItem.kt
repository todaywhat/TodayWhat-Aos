package com.onmi.domain.model.meal

data class MealMenuItem(
    val name: String,
    val allergyIds: List<Int>,
)
