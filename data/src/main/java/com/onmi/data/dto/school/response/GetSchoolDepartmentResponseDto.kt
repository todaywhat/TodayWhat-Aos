package com.onmi.data.dto.school.response

import com.onmi.domain.model.school.response.GetSchoolDepartmentResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSchoolDepartmentResponseDto(
    @SerialName("DDDEP_NM")
    val department: String,
)

fun GetSchoolDepartmentResponseDto.toModel(): GetSchoolDepartmentResponseModel {
    return GetSchoolDepartmentResponseModel(
        department = this.department
    )
}