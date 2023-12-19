package khs.onmi.school.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchSchoolByNameResponseListDto(
    @SerialName("schoolInfo")
    val schoolInfo: List<CommonResponse<SearchSchoolByNameResponseDto>>
)