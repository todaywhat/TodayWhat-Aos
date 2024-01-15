package khs.onmi.main.viewmodel

import androidx.lifecycle.ViewModel
import com.onmi.database.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.main.viewmodel.container.MainSideEffect
import khs.onmi.main.viewmodel.container.MainState
import khs.onmi.meal.domain.usecase.GetTodayMealsUseCase
import khs.onmi.timetable.domain.usecase.GetTodayTimeTableUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodayTimeTableUseCase: GetTodayTimeTableUseCase,
    private val getTodayMealsUseCase: GetTodayMealsUseCase,
    private val onmiDao: UserDao,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container = container<MainState, MainSideEffect>(
        MainState()
    )

    init {
        getTodayTimeTable()
        getUserInfo()
        getTodayMeals()
    }

    private fun getUserInfo() = intent {
        kotlin.runCatching {
            onmiDao.getUserInfo()
        }.onSuccess {
            if (it != null) {
                reduce {
                    state.copy(
                        schoolName = it.schoolName,
                        grade = it.grade,
                        `class` = it.classroom
                    )
                }
            } else {
                postSideEffect(MainSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
            }
        }.onFailure {
            postSideEffect(MainSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
        }
    }

    private fun getTodayTimeTable() = intent {
        getTodayTimeTableUseCase()
            .onSuccess { response ->
                reduce {
                    state.copy(timetable = response)
                }
            }.onFailure {
                postSideEffect(
                    MainSideEffect.ShowToast(
                        message = it.message ?: "알 수 없는 에러가 발생했습니다."
                    )
                )
            }
    }

    private fun getTodayMeals() = intent {
        getTodayMealsUseCase()
            .onSuccess { response ->
                reduce {
                    state.copy(
                        breakfast = response.breakfast,
                        lunch = response.lunch,
                        dinner = response.dinner
                    )
                }
            }.onFailure {
                postSideEffect(
                    MainSideEffect.ShowToast(
                        message = it.message ?: "알 수 없는 에러가 발생했습니다."
                    )
                )
            }
    }
}