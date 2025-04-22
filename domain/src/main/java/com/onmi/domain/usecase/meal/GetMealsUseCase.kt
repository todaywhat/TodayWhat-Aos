package com.onmi.domain.usecase.meal

import com.onmi.database.user.UserDao
import com.onmi.domain.exception.NeisException
import com.onmi.domain.exception.NeisResult
import com.onmi.domain.model.meal.response.GetMealsResponseModel
import com.onmi.domain.repository.MealRepository
import kotlinx.coroutines.flow.first
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException
import javax.inject.Inject

sealed interface MealException {
    /** 급식 정보가 없음 */
    data object DataEmpty : MealException

    /** 인터넷 연결 X */
    data object InternetDisconnected : MealException

    /**
     *  알 수 없는 에러 with errorCode
     *  - 사용자가 알 필요가 없는 remote error
     *  - 코드상 문제로 인한 exception
     *  */
    data class Unknown(val errorCode: String) : MealException
}

sealed interface MealState {
    data object Loading : MealState

    data class Success(val response: GetMealsResponseModel) : MealState

    data class Failure(val exception: MealException) : MealState
}

class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository,
    private val userDao: UserDao,
) {
    suspend operator fun invoke(targetDate: String) = runCatching {
        val userInfo = userDao.getUserInfo().first() ?: return MealState.Failure(
            MealException.Unknown(NeisResult.UNKNOWN_ERROR.code)
        )

        repository.getMeals(
            educationCode = userInfo.educationCode,
            schoolCode = userInfo.schoolCode,
            date = targetDate
        )
    }.fold(
        onSuccess = { result ->
            MealState.Success(result)
        },
        onFailure = { exception ->
            val error = when (exception) {
                is NeisException -> {
                    when {
                        exception.result == NeisResult.DATA_NOT_FOUND -> MealException.DataEmpty

                        else -> MealException.Unknown(exception.result.code)
                    }
                }

                is UnresolvedAddressException, is UnknownHostException -> MealException.InternetDisconnected

                else -> MealException.Unknown(NeisResult.UNKNOWN_ERROR.code)
            }

            MealState.Failure(error)
        }
    )
}