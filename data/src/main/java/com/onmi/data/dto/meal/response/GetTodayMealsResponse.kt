package com.onmi.data.dto.meal.response

import kotlinx.serialization.Serializable

@Serializable
data class GetTodayMealsResponse(
    val breakfast: Pair<List<String>, String>,
    val lunch: Pair<List<String>, String>,
    val dinner: Pair<List<String>, String>,
)

//fun GetTodayMealsResponse.toModel(): GetTodayMealsResponseModel {
//    return GetTodayMealsResponseModel(
//        breakfast = this.breakfast,
//        lunch = this.lunch,
//        dinner = this.dinner
//    )
//}

