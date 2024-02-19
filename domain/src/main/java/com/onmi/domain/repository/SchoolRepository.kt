package com.onmi.domain.repository

import com.onmi.domain.model.school.response.GetSchoolDepartmentResponseModel
import com.onmi.domain.model.school.response.SearchSchoolByNameResponseModel

interface SchoolRepository {

    suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseModel>

    suspend fun getSchoolDepartmentsInfo(
        educationCode: String,
        schoolCode: String,
    ): List<GetSchoolDepartmentResponseModel>
}