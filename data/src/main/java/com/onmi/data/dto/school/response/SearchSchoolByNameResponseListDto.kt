package com.onmi.data.dto.school.response

import com.onmi.data.dto.CommonResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchSchoolByNameResponseListDto(
    @SerialName("schoolInfo")
    val schoolInfo: List<CommonResponse<SearchSchoolByNameResponseDto>>
)