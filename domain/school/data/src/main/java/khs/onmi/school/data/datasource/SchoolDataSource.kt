package khs.onmi.school.data.datasource

import khs.onmi.school.data.dto.request.SearchSchoolByNameRequestDto
import khs.onmi.school.data.dto.response.SearchSchoolByNameResponseDto

interface SchoolDataSource {

    suspend fun searchSchoolByName(searchKeyword: SearchSchoolByNameRequestDto): List<SearchSchoolByNameResponseDto>
}