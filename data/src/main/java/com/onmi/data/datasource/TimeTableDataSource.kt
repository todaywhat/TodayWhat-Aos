package com.onmi.data.datasource

import com.onmi.data.dto.timetable.response.GetElementarySchoolTimTableResponse
import com.onmi.data.dto.timetable.response.GetHighSchoolTimeTableResponseListDto
import com.onmi.data.dto.timetable.response.GetMiddleSchoolTimeTableResponse
import com.onmi.data.dto.timetable.response.GetSpecialSchoolTimeTableResponse
import com.onmi.data.service.TimeTableService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class TimeTableDataSource @Inject constructor(
    private val httpClient: HttpClient,
) : TimeTableService {

    override suspend fun getTimeTable(
        schoolCode: String,
        schoolType: String,
        educationCode: String,
        grade: Int,
        `class`: Int,
        department: String,
        date: String,
    ): List<String> {
        return when (schoolType) {
            "els" -> httpClient.get {
                url("/hub/elsTimetable")
                parameter("ATPT_OFCDC_SC_CODE", educationCode)
                parameter("SD_SCHUL_CODE", schoolCode)
                parameter("DDDEP_NM", department)
                parameter("GRADE", grade)
                parameter("CLASS_NM", `class`)
                parameter("ALL_TI_YMD", date)
            }.body<GetElementarySchoolTimTableResponse>().timetable[1].row.map { it.subject }

            "mis" -> httpClient.get {
                url("/hub/misTimetable")
                parameter("ATPT_OFCDC_SC_CODE", educationCode)
                parameter("SD_SCHUL_CODE", schoolCode)
                parameter("DDDEP_NM", department)
                parameter("GRADE", grade)
                parameter("CLASS_NM", `class`)
                parameter("ALL_TI_YMD", date)
            }.body<GetMiddleSchoolTimeTableResponse>().timetable[1].row.map { it.subject }

            "his" -> httpClient.get {
                url("/hub/hisTimetable")
                parameter("ATPT_OFCDC_SC_CODE", educationCode)
                parameter("SD_SCHUL_CODE", schoolCode)
                parameter("DDDEP_NM", department)
                parameter("GRADE", grade)
                parameter("CLASS_NM", `class`)
                parameter("ALL_TI_YMD", date)
            }.body<GetHighSchoolTimeTableResponseListDto>().timetable[1].row.map { it.subject }

            "sps" -> httpClient.get {
                url("/hub/spsTimetable")
                parameter("ATPT_OFCDC_SC_CODE", educationCode)
                parameter("SD_SCHUL_CODE", schoolCode)
                parameter("DDDEP_NM", department)
                parameter("GRADE", grade)
                parameter("CLASS_NM", `class`)
                parameter("ALL_TI_YMD", date)
            }.body<GetSpecialSchoolTimeTableResponse>().timetable[1].row.map { it.subject }

            else -> emptyList()
        }
    }
}