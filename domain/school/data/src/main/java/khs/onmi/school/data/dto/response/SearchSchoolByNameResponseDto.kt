package khs.onmi.school.data.dto.response

import khs.onmi.school.domain.model.response.SearchSchoolByNameResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchSchoolByNameResponseDto(
    @SerialName("SCHUL_NM")
    val schoolName: String = "",

    @SerialName("ORG_RDNMA")
    val schoolLocation: String = "",
)

fun SearchSchoolByNameResponseDto.toModel(): SearchSchoolByNameResponseModel {
    return SearchSchoolByNameResponseModel(
        schoolName = this.schoolName,
        schoolLocation = this.schoolLocation
    )
}