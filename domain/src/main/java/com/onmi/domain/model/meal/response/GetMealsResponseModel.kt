package com.onmi.domain.model.meal.response

data class GetMealsResponseModel(
    val breakfast: Pair<List<String>, String>,
    val lunch: Pair<List<String>, String>,
    val dinner: Pair<List<String>, String>,
)
