package khs.onmi.school.domain.repository

import khs.onmi.school.domain.model.response.GetSchoolDepartmentResponseModel
import khs.onmi.school.domain.model.response.SearchSchoolByNameResponseModel

interface SchoolRepository {

    suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseModel>

    suspend fun getSchoolDepartmentsInfo(
        educationCode: String,
        schoolCode: String,
    ): List<GetSchoolDepartmentResponseModel>
}