package com.onmi.domain.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

object DateUtils {
    fun checkIsWeekend(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            when (LocalDate.now().dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> true
                else -> false
            }
        } else {
            val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY
        }
    }

    fun getNextMondayDate(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val today = LocalDate.now()
            val nextMonday = when (today.dayOfWeek) {
                DayOfWeek.SATURDAY -> today.plusDays(2)
                else -> today.plusDays(1)
            }
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.getDefault())
            nextMonday.format(formatter)
        } else {
            val calendar = Calendar.getInstance()
            val nextMonday = when (calendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.SATURDAY -> {
                    calendar.add(Calendar.DAY_OF_MONTH, 2)
                    calendar
                }

                else -> {
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                    calendar
                }
            }
            val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            formatter.format(nextMonday.time)
        }
    }
}