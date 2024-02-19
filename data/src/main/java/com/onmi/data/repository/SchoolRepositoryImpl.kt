package com.onmi.data.repository

import com.onmi.data.dto.school.response.toModel
import com.onmi.data.service.SchoolService
import com.onmi.domain.model.school.response.GetSchoolDepartmentResponseModel
import com.onmi.domain.model.school.response.SearchSchoolByNameResponseModel
import com.onmi.domain.repository.SchoolRepository
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val service: SchoolService,
) : SchoolRepository {

    override suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseModel> {
        return service.searchSchoolByName(searchKeyword = searchKeyword).map { it.toModel() }
    }

    override suspend fun getSchoolDepartmentsInfo(
        educationCode: String,
        schoolCode: String,
    ): List<GetSchoolDepartmentResponseModel> {
        return service.getSchoolDepartmentsInfo(
            educationCode = educationCode,
            schoolCode = schoolCode
        ).map { it.toModel() }
    }
}