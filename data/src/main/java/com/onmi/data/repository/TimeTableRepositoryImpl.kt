package com.onmi.data.repository

import com.onmi.data.service.TimeTableService
import com.onmi.domain.repository.TimeTableRepository
import javax.inject.Inject

class TimeTableRepositoryImpl @Inject constructor(
    private val service: TimeTableService,
) : TimeTableRepository {

    override suspend fun getTimeTable(
        schoolCode: String,
        schoolType: String,
        educationCode: String,
        grade: Int,
        `class`: Int,
        department: String,
        date: String,
    ): List<String> {
        return service.getTimeTable(
            schoolCode = schoolCode,
            schoolType = schoolType,
            educationCode = educationCode,
            grade = grade,
            `class` = `class`,
            department = department,
            date = date
        )
    }
}