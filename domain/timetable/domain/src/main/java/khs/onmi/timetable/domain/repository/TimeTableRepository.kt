package khs.onmi.timetable.domain.repository

interface TimeTableRepository {

    suspend fun getTimeTable(
        grade: Int,
        `class`: Int,
        department: String,
        beginningDate: String,
        endDate: String,
    ): List<String>
}