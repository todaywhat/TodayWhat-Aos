package com.onmi.domain.model.meal.response

import com.onmi.domain.model.meal.MealMenuItem

data class GetMealsResponseModel(
    val breakfast: Pair<List<MealMenuItem>, String>,
    val lunch: Pair<List<MealMenuItem>, String>,
    val dinner: Pair<List<MealMenuItem>, String>,
)
