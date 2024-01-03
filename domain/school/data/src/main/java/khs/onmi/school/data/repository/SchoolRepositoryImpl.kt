package khs.onmi.school.data.repository

import khs.onmi.school.data.datasource.SchoolDataSource
import khs.onmi.school.data.dto.response.toModel
import khs.onmi.school.domain.model.response.GetSchoolDepartmentResponseModel
import khs.onmi.school.domain.model.response.SearchSchoolByNameResponseModel
import khs.onmi.school.domain.repository.SchoolRepository
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val dataSource: SchoolDataSource,
) : SchoolRepository {
    override suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseModel> {
        return dataSource.searchSchoolByName(searchKeyword = searchKeyword).map { it.toModel() }
    }

    override suspend fun getSchoolDepartmentsInfo(
        educationCode: String,
        schoolCode: String,
    ): List<GetSchoolDepartmentResponseModel> {
        return dataSource.getSchoolDepartmentsInfo(
            educationCode = educationCode,
            schoolCode = schoolCode
        ).map { it.toModel() }
    }
}