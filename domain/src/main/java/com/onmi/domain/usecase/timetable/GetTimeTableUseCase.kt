package com.onmi.domain.usecase.timetable

import com.onmi.domain.exception.NeisException
import com.onmi.domain.exception.NeisResult
import com.onmi.domain.repository.TimeTableRepository
import com.onmi.domain.usecase.user.GetUserInfoFlowUseCase
import com.onmi.domain.util.DateUtils
import kotlinx.coroutines.flow.first
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException
import javax.inject.Inject

sealed interface TimeTableException {
    /** 시간표 정보가 없음 */
    data object DataEmpty : TimeTableException

    /**  임시 시간표 */
    data object TemporaryTimeTable : TimeTableException

    /** 인터넷 연결 X */
    data object InternetDisconnected : TimeTableException

    /**
     *  알 수 없는 에러 with errorCode
     *  - 사용자가 알 필요가 없는 remote error
     *  - 코드상 문제로 인한 exception
     *  */
    data class Unknown(val errorCode: String) : TimeTableException
}

sealed interface TimeTableState {
    data object Loading : TimeTableState

    data class Success(val response: List<String>) : TimeTableState

    data class Failure(val exception: TimeTableException) : TimeTableState
}

class GetTimeTableUseCase @Inject constructor(
    private val repository: TimeTableRepository,
    private val getUserInfoFlowUseCase: GetUserInfoFlowUseCase,
) {
    suspend operator fun invoke(targetDate: String) = runCatching {
        val userInfo = getUserInfoFlowUseCase().first()

        repository.getTimeTable(
            schoolCode = userInfo.schoolCode,
            schoolType = convertSchoolTypeToKey(userInfo.schoolType),
            educationCode = userInfo.educationCode,
            grade = userInfo.grade,
            `class` = userInfo.classroom,
            department = userInfo.department,
            date = targetDate
        )
    }.fold(
        onSuccess = { result ->
            TimeTableState.Success(result)
        },
        onFailure = { exception ->
            val error = when (exception) {
                is NeisException -> {
                    when {
                        exception.result == NeisResult.DATA_NOT_FOUND && DateUtils.isMarch() -> TimeTableException.TemporaryTimeTable

                        exception.result == NeisResult.DATA_NOT_FOUND -> TimeTableException.DataEmpty

                        else -> TimeTableException.Unknown(exception.result.code)
                    }
                }

                is UnresolvedAddressException, is UnknownHostException -> TimeTableException.InternetDisconnected

                else -> TimeTableException.Unknown(NeisResult.UNKNOWN_ERROR.code)
            }

            TimeTableState.Failure(error)
        }
    )

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