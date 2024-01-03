package khs.onmi.school.data.datasource

import khs.onmi.school.data.dto.response.GetSchoolDepartmentResponseDto
import khs.onmi.school.data.dto.response.SearchSchoolByNameResponseDto

interface SchoolDataSource {

    suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseDto>

    suspend fun getSchoolDepartmentsInfo(
        educationCode: String,
        schoolCode: String,
    ): List<GetSchoolDepartmentResponseDto>
}