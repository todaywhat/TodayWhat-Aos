package com.onmi.data.dto.meal.response

import com.onmi.data.dto.BaseResponse
import com.onmi.data.dto.CommonResponse
import com.onmi.data.dto.ErrorResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTodayMealsListResponse(
    @SerialName("RESULT")
    override val result: ErrorResult? = null,
    @SerialName("mealServiceDietInfo")
    val meals: List<CommonResponse<SchoolMealInfo>>? = null,
) : BaseResponse
