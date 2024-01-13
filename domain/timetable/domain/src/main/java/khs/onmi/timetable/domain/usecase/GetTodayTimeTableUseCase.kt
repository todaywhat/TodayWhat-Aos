package khs.onmi.timetable.domain.usecase

import khs.onmi.timetable.domain.repository.TimeTableRepository
import javax.inject.Inject

class GetTodayTimeTableUseCase @Inject constructor(
    private val repository: TimeTableRepository,
) {
    suspend operator fun invoke(
        grade: Int,
        `class`: Int,
        department: String,
        beginningDate: String,
        endDate: String,
    ) = kotlin.runCatching {
        repository.getTimeTable(
            grade = grade,
            `class` = `class`,
            department = department,
            beginningDate = beginningDate,
            endDate = endDate,
        )
    }
}