package com.onmi.data.dto.timetable.response

import com.onmi.data.dto.CommonResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHighSchoolTimeTableResponse(
    @SerialName("hisTimetable")
    val timetable: List<CommonResponse<GetTimeTableResponse>>? = null,
)