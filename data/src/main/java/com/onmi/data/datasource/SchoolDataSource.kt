package com.onmi.data.datasource

import com.onmi.data.dto.school.response.GetSchoolDepartmentResponseDto
import com.onmi.data.dto.school.response.SearchSchoolByNameResponseDto

interface SchoolDataSource {

    suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseDto>

    suspend fun getSchoolDepartmentsInfo(
        educationCode: String,
        schoolCode: String,
    ): List<GetSchoolDepartmentResponseDto>
}