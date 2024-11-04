package com.onmi.domain.usecase.meal

import com.onmi.database.UserDao
import com.onmi.domain.repository.MealRepository
import com.onmi.domain.util.DateUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetTodayMealsUseCase @Inject constructor(
    private val repository: MealRepository,
    private val userDao: UserDao,
) {
    suspend operator fun invoke(targetDate: LocalDate? = null) = kotlin.runCatching {
        val userInfo = runBlocking {
            userDao.getUserInfo().first()
        } ?: throw RuntimeException("fail to get user info")

        repository.getMeals(
            educationCode = userInfo.educationCode,
            schoolCode = userInfo.schoolCode,
            date = targetDate?.toString()?.replace("-", "")
                ?: if (DateUtils.checkIsWeekend() && userInfo.isSkipWeekend) {
                    DateUtils.getNextMondayDate()
                } else {
                    convertMillisToDateString(System.currentTimeMillis())
                }
        )
    }

    private fun convertMillisToDateString(millis: Long): String {
        val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val date = Date(millis)
        return formatter.format(date)
    }
}