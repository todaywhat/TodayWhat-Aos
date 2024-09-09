package com.onmi.data.dto.school.response

import com.onmi.domain.model.school.response.SearchSchoolByNameResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchSchoolByNameResponseDto(
    @SerialName("ATPT_OFCDC_SC_CODE")
    val educationCode: String,

    @SerialName("SD_SCHUL_CODE")
    val schoolCode: String,

    @SerialName("SCHUL_NM")
    val schoolName: String,

    @SerialName("ORG_RDNMA")
    val schoolLocation: String,

    @SerialName("SCHUL_KND_SC_NM")
    val schoolType: String,
)

fun SearchSchoolByNameResponseDto.toModel(): SearchSchoolByNameResponseModel {
    return SearchSchoolByNameResponseModel(
        educationCode = this.educationCode,
        schoolCode = this.schoolCode,
        schoolName = this.schoolName,
        schoolLocation = this.schoolLocation,
        schoolType = this.schoolType
    )
}