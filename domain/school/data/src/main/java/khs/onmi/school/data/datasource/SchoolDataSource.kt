package khs.onmi.school.data.datasource

import khs.onmi.school.data.dto.response.SearchSchoolByNameResponseDto

interface SchoolDataSource {

    suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseDto>
}