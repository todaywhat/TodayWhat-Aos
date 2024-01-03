package khs.onmi.school.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import khs.onmi.school.data.dto.response.GetSchoolDepartmentResponseDto
import khs.onmi.school.data.dto.response.GetSchoolDepartmentResponseListDto
import khs.onmi.school.data.dto.response.SearchSchoolByNameResponseDto
import khs.onmi.school.data.dto.response.SearchSchoolByNameResponseListDto
import javax.inject.Inject


class SchoolDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : SchoolDataSource {

    override suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseDto> {
        return httpClient.get {
            url("/hub/schoolInfo")
            parameter("SCHUL_NM", searchKeyword)
        }.body<SearchSchoolByNameResponseListDto>().schoolInfo[1].row.map { response ->
            SearchSchoolByNameResponseDto(
                educationCode = response.educationCode,
                schoolCode = response.schoolCode,
                schoolName = response.schoolName,
                schoolLocation = response.schoolLocation
            )
        }
    }

    override suspend fun getSchoolDepartmentsInfo(
        educationCode: String,
        schoolCode: String,
    ): List<GetSchoolDepartmentResponseDto> {
        return httpClient.get {
            url("/hub/schoolMajorinfo")
            parameter("ATPT_OFCDC_SC_CODE", educationCode)
            parameter("SD_SCHUL_CODE", schoolCode)
        }.body<GetSchoolDepartmentResponseListDto>().schoolMajorInfo[1].row.map { response ->
            GetSchoolDepartmentResponseDto(
                department = response.department
            )
        }
    }
}