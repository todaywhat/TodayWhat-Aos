package com.onmi.data.datasource

import com.onmi.data.dto.timetable.response.GetElementarySchoolTimTableResponse
import com.onmi.data.dto.timetable.response.GetHighSchoolTimeTableResponse
import com.onmi.data.dto.timetable.response.GetMiddleSchoolTimeTableResponse
import com.onmi.data.dto.timetable.response.GetSpecialSchoolTimeTableResponse
import com.onmi.data.service.TimeTableService
import com.onmi.data.utils.bodyOrThrow
import io.ktor.client.HttpClient
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
        return fetchTimeTable(
            schoolType, educationCode, schoolCode, grade, `class`, date, department
        ) ?: fetchTimeTable(
            schoolType, educationCode, schoolCode, grade, `class`, date
        ) ?: emptyList()
    }


    private suspend fun fetchTimeTable(
        schoolType: String,
        educationCode: String,
        schoolCode: String,
        grade: Int,
        `class`: Int,
        date: String,
        department: String? = null,
    ): List<String>? {
        val response = httpClient.get {
            url("/hub/${schoolType}Timetable")
            parameter("ATPT_OFCDC_SC_CODE", educationCode)
            parameter("SD_SCHUL_CODE", schoolCode)
            parameter("DDDEP_NM", department)
            parameter("GRADE", grade)
            parameter("CLASS_NM", `class`)
            parameter("ALL_TI_YMD", date)
        }

        return when (schoolType) {
            "els" -> response.bodyOrThrow<GetElementarySchoolTimTableResponse>().timetable?.getOrNull(1)?.row
                ?.distinctBy { it.period }
                ?.map { it.subject }

            "mis" -> response.bodyOrThrow<GetMiddleSchoolTimeTableResponse>().timetable?.getOrNull(1)?.row
                ?.distinctBy { it.period }
                ?.map { it.subject }

            "his" -> response.bodyOrThrow<GetHighSchoolTimeTableResponse>().timetable?.getOrNull(1)?.row
                ?.distinctBy { it.period }
                ?.map { it.subject }

            "sps" -> response.bodyOrThrow<GetSpecialSchoolTimeTableResponse>().timetable?.getOrNull(1)?.row
                ?.distinctBy { it.period }
                ?.map { it.subject }

            else -> emptyList()
        }
    }
}