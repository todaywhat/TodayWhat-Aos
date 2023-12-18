package khs.onmi.school.domain.repository

import khs.onmi.school.domain.model.request.SearchSchoolByNameRequestModel
import khs.onmi.school.domain.model.response.SearchSchoolByNameResponseModel

interface SchoolRepository {

    suspend fun searchSchoolByName(searchKeyword: SearchSchoolByNameRequestModel): List<SearchSchoolByNameResponseModel>
}