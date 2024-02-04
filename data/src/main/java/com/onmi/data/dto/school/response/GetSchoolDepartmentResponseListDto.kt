package com.onmi.data.dto.school.response

import com.onmi.data.dto.CommonResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSchoolDepartmentResponseListDto(
    @SerialName("schoolMajorinfo")
    val schoolMajorInfo: List<CommonResponse<GetSchoolDepartmentResponseDto>>
)
