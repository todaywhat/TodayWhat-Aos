package com.onmi.data.dto.meal.response

import com.onmi.data.dto.CommonResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTodayMealsListResponse(
    @SerialName("mealServiceDietInfo")
    val meals: List<CommonResponse<SchoolMealInfo>>,
)
