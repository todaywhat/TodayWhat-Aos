package com.onmi.data.dto.meal.response

import com.onmi.domain.model.meal.MealMenuItem
import com.onmi.domain.model.meal.response.GetMealsResponseModel

data class GetTodayMealsResponse(
    val breakfast: Pair<List<MealMenuItem>, String>,
    val lunch: Pair<List<MealMenuItem>, String>,
    val dinner: Pair<List<MealMenuItem>, String>,
)

fun GetTodayMealsResponse.toModel(): GetMealsResponseModel {
    return GetMealsResponseModel(
        breakfast = this.breakfast,
        lunch = this.lunch,
        dinner = this.dinner
    )
}

