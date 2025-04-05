package com.onmi.data.dto.school.response

import com.onmi.data.dto.BaseResponse
import com.onmi.data.dto.CommonResponse
import com.onmi.data.dto.ErrorResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSchoolDepartmentResponseListDto(
    @SerialName("RESULT")
    override val result: ErrorResult? = null,
    @SerialName("schoolMajorinfo")
    val schoolMajorInfo: List<CommonResponse<GetSchoolDepartmentResponseDto>>,
) : BaseResponse
