package khs.onmi.timetable.domain.usecase

import com.onmi.database.UserDao
import khs.onmi.timetable.domain.repository.TimeTableRepository
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetTodayTimeTableUseCase @Inject constructor(
    private val repository: TimeTableRepository,
    private val onmiDao: UserDao,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        val userInfo =
            runBlocking { onmiDao.getUserInfo() } ?: throw RuntimeException("fail to get user info")

        repository.getTimeTable(
            schoolCode = userInfo.schoolCode,
            educationCode = userInfo.educationCode,
            grade = userInfo.grade,
            `class` = userInfo.classroom,
            department = userInfo.department,
            beginningDate = convertMillisToDateString(System.currentTimeMillis()),
            endDate = convertMillisToDateString(System.currentTimeMillis()),
        )
    }

    private fun convertMillisToDateString(millis: Long): String {
        val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val date = Date(millis)
        return formatter.format(date)
    }
}