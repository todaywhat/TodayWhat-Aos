package khs.onmi.timetable.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTimeTableResponseDto(
    @SerialName("ITRT_CNTNT")
    val subject: String,
)
