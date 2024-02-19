package com.onmi.data.dto.meal.response

import com.onmi.domain.model.meal.response.GetMealsResponseModel
import kotlinx.serialization.Serializable

@Serializable
data class GetTodayMealsResponse(
    val breakfast: Pair<List<String>, String>,
    val lunch: Pair<List<String>, String>,
    val dinner: Pair<List<String>, String>,
)

fun GetTodayMealsResponse.toModel(): GetMealsResponseModel {
    return GetMealsResponseModel(
        breakfast = this.breakfast,
        lunch = this.lunch,
        dinner = this.dinner
    )
}

