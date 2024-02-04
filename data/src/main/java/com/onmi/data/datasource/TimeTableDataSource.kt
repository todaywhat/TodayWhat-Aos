package com.onmi.data.datasource

import com.onmi.data.dto.timetable.response.GetTimeTableResponseListDto
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
        educationCode: String,
        grade: Int,
        `class`: Int,
        department: String,
        beginningDate: String,
        endDate: String,
    ): List<String> {
        return httpClient.get {
            url("/hub/hisTimetable")
            parameter("ATPT_OFCDC_SC_CODE", educationCode)
            parameter("SD_SCHUL_CODE", schoolCode)
            parameter("DDDEP_NM", department)
            parameter("GRADE", grade)
            parameter("CLASS_NM", `class`)
            parameter("TI_FROM_YMD", beginningDate)
            parameter("TI_TO_YMD", endDate)
        }.body<GetTimeTableResponseListDto>().hisTimetable[1].row.map { it.subject }
    }
}