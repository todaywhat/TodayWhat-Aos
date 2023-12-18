package khs.onmi.school.data.repository

import khs.onmi.school.data.datasource.SchoolDataSource
import khs.onmi.school.data.dto.request.toDto
import khs.onmi.school.data.dto.response.toModel
import khs.onmi.school.domain.model.request.SearchSchoolByNameRequestModel
import khs.onmi.school.domain.model.response.SearchSchoolByNameResponseModel
import khs.onmi.school.domain.repository.SchoolRepository
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val dataSource: SchoolDataSource,
) : SchoolRepository {
    override suspend fun searchSchoolByName(searchKeyword: SearchSchoolByNameRequestModel): List<SearchSchoolByNameResponseModel> {
        return dataSource.searchSchoolByName(searchKeyword = searchKeyword.toDto())
            .map { it.toModel() }
    }
}