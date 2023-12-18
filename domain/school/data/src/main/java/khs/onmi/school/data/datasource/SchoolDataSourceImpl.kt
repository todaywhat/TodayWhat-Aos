package khs.onmi.school.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import khs.onmi.school.data.dto.request.SearchSchoolByNameRequestDto
import khs.onmi.school.data.dto.response.SearchSchoolByNameResponseDto
import javax.inject.Inject


class SchoolDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : SchoolDataSource {

    override suspend fun searchSchoolByName(searchKeyword: SearchSchoolByNameRequestDto): List<SearchSchoolByNameResponseDto> {
        return httpClient.get {
            url("/schoolInfo")
            setBody(searchKeyword)
        }.body()
    }
}