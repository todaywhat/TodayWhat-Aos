package com.onmi.domain.usecase.timetable

import com.onmi.database.UserDao
import com.onmi.domain.exception.NeisException
import com.onmi.domain.exception.NeisResult
import com.onmi.domain.repository.TimeTableRepository
import com.onmi.domain.util.DateUtils
import kotlinx.coroutines.flow.first
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

sealed interface GetTimeTableException {
    data object DataEmpty : GetTimeTableException
    data object TemporaryTimeTable : GetTimeTableException
    data object InternetDisconnected : GetTimeTableException
    data class Unknown(val errorCode: String) : GetTimeTableException
}

sealed interface GetTimeTableState {
    data class Success(val response: List<String>) : GetTimeTableState

    data class Failure(val exception: GetTimeTableException) : GetTimeTableState
}

class GetTimeTableUseCase @Inject constructor(
    private val repository: TimeTableRepository,
    private val userDao: UserDao,
) {
    suspend operator fun invoke(): GetTimeTableState {
        val userInfo = userDao.getUserInfo().first()
            ?: return GetTimeTableState.Failure(GetTimeTableException.Unknown(NeisResult.UNKNOWN_ERROR.code))

        val date = when {
            DateUtils.checkIsWeekend() && userInfo.isSkipWeekend -> DateUtils.getNextMondayDate()
            DateUtils.checkIsAfterDinner() && userInfo.isShowNextDayInfoAfterDinner -> DateUtils.getNextDayDate()
            else -> convertMillisToDateString(System.currentTimeMillis())
        }

        return runCatching {
            repository.getTimeTable(
                schoolCode = userInfo.schoolCode,
                schoolType = convertSchoolTypeToKey(userInfo.schoolType),
                educationCode = userInfo.educationCode,
                grade = userInfo.grade,
                `class` = userInfo.classroom,
                department = userInfo.department,
                date = date
            )
        }.fold(
            onSuccess = { result ->
                GetTimeTableState.Success(result)
            },
            onFailure = { exception ->
                val error = when (exception) {
                    is NeisException -> when (exception.result) {
                        NeisResult.DATA_NOT_FOUND -> {
                            // TODO: 3월임을 구분하는 로직 추가
                            if (true) GetTimeTableException.TemporaryTimeTable
                            else GetTimeTableException.DataEmpty
                        }

                        else -> GetTimeTableException.Unknown(exception.result.code)
                    }

                    is UnknownHostException -> GetTimeTableException.InternetDisconnected
                    else -> GetTimeTableException.Unknown(NeisResult.UNKNOWN_ERROR.code)
                }
                GetTimeTableState.Failure(error)
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