package com.onmi.data.datasourceimpl

import com.onmi.data.dto.timetable.response.GetElementarySchoolTimTableResponse
import com.onmi.data.dto.timetable.response.GetHighSchoolTimeTableResponse
import com.onmi.data.dto.timetable.response.GetMiddleSchoolTimeTableResponse
import com.onmi.data.dto.timetable.response.GetSpecialSchoolTimeTableResponse
import com.onmi.data.datasource.TimeTableDataSource
import com.onmi.data.utils.bodyOrThrow
import com.onmi.domain.model.school.SchoolType
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class TimeTableDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : TimeTableDataSource {

    override suspend fun getTimeTable(
        schoolCode: String,
        schoolType: SchoolType,
        educationCode: String,
        grade: Int,
        `class`: Int,
        department: String?,
        date: String,
    ): List<String>? {
        val response = httpClient.get {
            url("/hub/${schoolType.key}Timetable")
            parameter("ATPT_OFCDC_SC_CODE", educationCode)
            parameter("SD_SCHUL_CODE", schoolCode)
            if (department != null) parameter("DDDEP_NM", department)
            parameter("GRADE", grade)
            parameter("CLASS_NM", `class`)
            parameter("ALL_TI_YMD", date)
        }

        return when (schoolType) {
            SchoolType.Elementary -> response.bodyOrThrow<GetElementarySchoolTimTableResponse>().timetable

            SchoolType.High -> response.bodyOrThrow<GetMiddleSchoolTimeTableResponse>().timetable

            SchoolType.Middle -> response.bodyOrThrow<GetHighSchoolTimeTableResponse>().timetable

            SchoolType.Special -> response.bodyOrThrow<GetSpecialSchoolTimeTableResponse>().timetable
        }?.getOrNull(1)?.row
            ?.distinctBy { it.period }
            ?.map { it.subject }
    }
}