package com.onmi.domain.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    fun convertMillisToDateString(millis: Long): String {
        val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val date = Date(millis)
        return formatter.format(date)
    }

    /**
     * UTC 자정 기준 millis 를 yyyyMMdd 로 변환한다.
     * Compose Material3 DatePicker 가 선택 날짜를 UTC 자정으로 돌려주기 때문에,
     * 기기 타임존을 따르는 [convertMillisToDateString] 을 쓰면 UTC 음수 오프셋 지역에서 하루가 밀린다.
     */
    fun convertUtcMillisToDateString(millis: Long): String {
        val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        return formatter.format(Date(millis))
    }

    /* [convertUtcMillisToDateString] 의 역변환. 형식이 잘못되면 null 을 돌려준다. */
    fun convertDateStringToUtcMillis(dateString: String): Long? {
        return runCatching {
            SimpleDateFormat("yyyyMMdd", Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }.parse(dateString)?.time
        }.getOrNull()
    }

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

    /* 7시 이후를 저녁시간으로 간주한다. */
    fun checkIsAfterDinner(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now().isAfter(LocalTime.of(7, 0, 0))
        } else {
            Calendar.getInstance().time.after(
                SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse("07:00:00")
            )
        }
    }

    fun getNextDayDate(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nextDay = LocalDate.now().plusDays(1)
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.getDefault())
            nextDay.format(formatter)
        } else {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            formatter.format(calendar.time)
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
            calendar.add(
                Calendar.DAY_OF_WEEK,
                when (calendar.get(Calendar.DAY_OF_WEEK)) {
                    Calendar.SATURDAY -> 2
                    Calendar.SUNDAY -> 1
                    else -> (Calendar.SATURDAY - calendar.get(Calendar.DAY_OF_WEEK) + 2) % 7
                }
            )
            val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            formatter.format(calendar.time)
        }
    }

    fun convertToMonthDay(dateString: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            convertWithDateTimeFormatter(dateString)
        } else {
            convertWithSimpleDateFormat(dateString)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertWithDateTimeFormatter(dateString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val outputFormatter = DateTimeFormatter.ofPattern("MM월 dd일 E요일")
        return LocalDate.parse(dateString, inputFormatter).format(outputFormatter)
    }

    private fun convertWithSimpleDateFormat(dateString: String): String {
        val inputFormatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("MM월 dd일 E요일", Locale.getDefault())
        return inputFormatter.parse(dateString)?.let { outputFormatter.format(it) }
            ?: throw IllegalArgumentException("잘못된 날짜 형식입니다")
    }

    /* 현재 월이 3월인지 여부를 판별합니다. */
    fun isMarch(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now(Clock.systemUTC()).monthValue == 3
        } else {
            val calendar = Calendar.getInstance()
            val month = calendar.get(Calendar.MONTH) + 1
            month == 3
        }
    }
}