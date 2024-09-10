package com.onmi.data.dto.timetable.response

import com.onmi.data.dto.CommonResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSpecialSchoolTimeTableResponse(
    @SerialName("spsTimetable")
    val timetable: List<CommonResponse<GetTimeTableResponseDto>>,
)
