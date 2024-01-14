package khs.onmi.timetable.data.repository

import khs.onmi.timetable.data.datasource.TimeTableDataSource
import khs.onmi.timetable.domain.repository.TimeTableRepository
import javax.inject.Inject

class TimeTableRepositoryImpl @Inject constructor(
    private val dataSource: TimeTableDataSource,
) : TimeTableRepository {
    override suspend fun getTimeTable(
        schoolCode: String,
        educationCode: String,
        grade: Int,
        `class`: Int,
        department: String,
        beginningDate: String,
        endDate: String,
    ): List<String> {
        return dataSource.getTimeTable(
            schoolCode = schoolCode,
            educationCode = educationCode,
            grade = grade,
            `class` = `class`,
            department = department,
            beginningDate = beginningDate,
            endDate = endDate
        )
    }
}