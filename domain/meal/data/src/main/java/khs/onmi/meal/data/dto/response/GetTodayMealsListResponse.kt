package khs.onmi.meal.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTodayMealsListResponse(
    @SerialName("mealServiceDietInfo")
    val meals: List<CommonResponse<SchoolMealInfo>>,
)
