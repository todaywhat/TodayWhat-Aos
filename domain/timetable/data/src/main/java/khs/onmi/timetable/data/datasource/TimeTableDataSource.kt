package khs.onmi.timetable.data.datasource

interface TimeTableDataSource {

    suspend fun getTimeTable(
        grade: Int,
        `class`: Int,
        department: String,
        beginningDate: String,
        endDate: String,
    ): List<String>
}