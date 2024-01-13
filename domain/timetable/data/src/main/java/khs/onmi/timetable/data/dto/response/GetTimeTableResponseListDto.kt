package khs.onmi.timetable.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTimeTableResponseListDto(
    @SerialName("hisTimetable")
    val hisTimetable: List<CommonResponse<GetTimeTableResponseDto>>,
)
