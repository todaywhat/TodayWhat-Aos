package com.onmi.data.dto.timetable.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTimeTableResponse(
    @SerialName("ITRT_CNTNT")
    val subject: String,
    @SerialName("PERIO")
    val period: String,
)
