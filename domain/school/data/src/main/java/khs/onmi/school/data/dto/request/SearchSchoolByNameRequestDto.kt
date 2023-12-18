package khs.onmi.school.data.dto.request

import khs.onmi.school.domain.model.request.SearchSchoolByNameRequestModel
import kotlinx.serialization.SerialName

data class SearchSchoolByNameRequestDto(
    @SerialName("SCHUL_NM")
    val searchKeyword: String,
)

fun SearchSchoolByNameRequestModel.toDto(): SearchSchoolByNameRequestDto {
    return SearchSchoolByNameRequestDto(
        searchKeyword = searchKeyword
    )
}