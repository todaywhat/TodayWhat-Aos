package khs.onmi.timetable.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import khs.onmi.timetable.data.dto.response.GetTimeTableResponseListDto
import javax.inject.Inject

class TimeTableDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : TimeTableDataSource {
    override suspend fun getTimeTable(
        grade: Int,
        `class`: Int,
        department: String,
        beginningDate: String,
        endDate: String,
    ): List<String> {
        return httpClient.get {
            url("/hub/hisTimetable")
            parameter("GRADE", grade)
            parameter("CLASS_NM", `class`)
            parameter("DDDEP_NM", department)
            parameter("TI_FROM_YMD", beginningDate)
            parameter("TI_TO_YMD", endDate)
        }.body<GetTimeTableResponseListDto>().hisTimetable[1].row.map { it.subject }
    }
}