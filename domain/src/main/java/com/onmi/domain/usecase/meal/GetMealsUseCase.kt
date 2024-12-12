package com.onmi.domain.usecase.meal

import android.os.Build
import com.onmi.database.UserDao
import com.onmi.domain.repository.MealRepository
import com.onmi.domain.util.DateUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository,
    private val userDao: UserDao,
) {
    suspend operator fun invoke(date: LocalDate? = null) = kotlin.runCatching {
        val userInfo = runBlocking {
            userDao.getUserInfo().first()
        } ?: throw RuntimeException("fail to get user info")

        val targetDate = when {
            date != null -> date.toString().replace("-", "")
            DateUtils.checkIsWeekend() && userInfo.isSkipWeekend -> DateUtils.getNextMondayDate()
            DateUtils.checkIsAfterDinner() && userInfo.isShowNextDayInfoAfterDinner -> DateUtils.getNextDayDate()
            else -> convertMillisToDateString(System.currentTimeMillis())
        }

        Pair(
            convertToMonthDay(dateString = targetDate),
            repository.getMeals(
                educationCode = userInfo.educationCode,
                schoolCode = userInfo.schoolCode,
                date = targetDate
            )
        )
    }

    private fun convertMillisToDateString(millis: Long): String {
        val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val date = Date(millis)
        return formatter.format(date)
    }

    private fun convertToMonthDay(dateString: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val outputFormatter = DateTimeFormatter.ofPattern("MM월 dd일")

            val date = LocalDate.parse(dateString, inputFormatter)
            date.format(outputFormatter)
        } else {
            val inputFormatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val outputFormatter = SimpleDateFormat("MM월 dd일", Locale.getDefault())

            val date = inputFormatter.parse(dateString)
            if (date != null) {
                outputFormatter.format(date)
            } else {
                throw IllegalArgumentException("Invalid date format")
            }
        }
    }
}