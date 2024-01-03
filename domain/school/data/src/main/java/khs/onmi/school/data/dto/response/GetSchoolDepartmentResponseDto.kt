package khs.onmi.school.data.dto.response

import khs.onmi.school.domain.model.response.GetSchoolDepartmentResponseModel
import khs.onmi.school.domain.model.response.SearchSchoolByNameResponseModel
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