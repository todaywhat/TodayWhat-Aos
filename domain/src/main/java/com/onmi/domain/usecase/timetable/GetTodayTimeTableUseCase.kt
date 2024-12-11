package com.onmi.domain.usecase.timetable

import com.onmi.database.UserDao
import com.onmi.domain.repository.TimeTableRepository
import com.onmi.domain.util.DateUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetTodayTimeTableUseCase @Inject constructor(
    private val repository: TimeTableRepository,
    private val userDao: UserDao,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        val userInfo =
            runBlocking { userDao.getUserInfo().first() }
                ?: throw RuntimeException("fail to get user info")

        repository.getTimeTable(
            schoolCode = userInfo.schoolCode,
            schoolType = convertSchoolTypeToKey(userInfo.schoolType),
            educationCode = userInfo.educationCode,
            grade = userInfo.grade,
            `class` = userInfo.classroom,
            department = userInfo.department,
            date = when {
                DateUtils.checkIsWeekend() && userInfo.isSkipWeekend -> DateUtils.getNextMondayDate()
                DateUtils.checkIsAfterDinner() && userInfo.isShowNextDayInfoAfterDinner -> DateUtils.getNextDayDate()
                else -> convertMillisToDateString(System.currentTimeMillis())
            }
        )
    }

    private fun convertMillisToDateString(millis: Long): String {
        val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val date = Date(millis)
        return formatter.format(date)
    }

    private fun convertSchoolTypeToKey(type: String): String {
        return when (type) {
            "초등학교" -> "els"
            "중학교" -> "mis"
            "고등학교" -> "his"
            "특수학교" -> "sps"
            else -> throw RuntimeException("알 수 없는 학교 타입입니다.")
        }
    }
}