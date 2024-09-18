package com.onmi.data.dto.timetable.response

import com.onmi.data.dto.CommonResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetElementarySchoolTimTableResponse(
    @SerialName("elsTimetable")
    val timetable: List<CommonResponse<GetTimeTableResponse>>? = null,
)