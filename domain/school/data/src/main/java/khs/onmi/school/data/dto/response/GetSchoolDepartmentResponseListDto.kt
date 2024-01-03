package khs.onmi.school.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSchoolDepartmentResponseListDto(
    @SerialName("schoolMajorinfo")
    val schoolMajorInfo: List<CommonResponse<GetSchoolDepartmentResponseDto>>
)
