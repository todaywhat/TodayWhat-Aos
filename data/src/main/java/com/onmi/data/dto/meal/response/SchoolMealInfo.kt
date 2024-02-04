package com.onmi.data.dto.meal.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolMealInfo(
    @SerialName("MMEAL_SC_NM") // 조식, 중식, 석식 구분
    val type: String,
    @SerialName("DDISH_NM") // 급식 정보
    val meal: String,
    @SerialName("CAL_INFO") // 칼로리 정보
    val kcal: String,
)
