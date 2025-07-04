package com.onmi.data.datasourceimpl

import com.onmi.data.dto.school.response.GetSchoolDepartmentResponseDto
import com.onmi.data.dto.school.response.GetSchoolDepartmentResponseListDto
import com.onmi.data.dto.school.response.SearchSchoolByNameResponseDto
import com.onmi.data.dto.school.response.SearchSchoolByNameResponseListDto
import com.onmi.data.datasource.SchoolDataSource
import com.onmi.data.utils.bodyOrThrow
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class SchoolDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : SchoolDataSource {

    override suspend fun searchSchoolByName(searchKeyword: String): List<SearchSchoolByNameResponseDto> {
        return httpClient.get {
            url("/hub/schoolInfo")
            parameter("SCHUL_NM", searchKeyword)
        }.bodyOrThrow<SearchSchoolByNameResponseListDto>().schoolInfo[1].row.map { response ->
            SearchSchoolByNameResponseDto(
                educationCode = response.educationCode,
                schoolCode = response.schoolCode,
                schoolName = response.schoolName,
                schoolLocation = response.schoolLocation,
                schoolType = response.schoolType
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
        }.bodyOrThrow<GetSchoolDepartmentResponseListDto>().schoolMajorInfo[1].row.map { response ->
            GetSchoolDepartmentResponseDto(
                department = response.department
            )
        }
    }
}