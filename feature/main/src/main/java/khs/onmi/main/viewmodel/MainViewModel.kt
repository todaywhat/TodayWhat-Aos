package khs.onmi.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onmi.database.UserDao
import com.onmi.domain.usecase.meal.GetMealsUseCase
import com.onmi.domain.usecase.timetable.GetTimeTableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.main.viewmodel.container.MainSideEffect
import khs.onmi.main.viewmodel.container.MainState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTimeTableUseCase: GetTimeTableUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val onmiDao: UserDao,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container = container<MainState, MainSideEffect>(MainState())

    init {
        getUserInfo()
    }

    private fun getUserInfo() = intent {
        kotlin.runCatching {
            onmiDao.getUserInfo()
        }.onSuccess {
            it.collectLatest { userEntity ->
                if (userEntity != null) {
                    getTodayTimeTable()
                    getTodayMeals()
                    reduce {
                        state.copy(
                            schoolName = userEntity.schoolName,
                            grade = userEntity.grade,
                            `class` = userEntity.classroom
                        )
                    }
                } else {
                    postSideEffect(MainSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
                }
            }
        }.onFailure {
            postSideEffect(MainSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
        }
    }

    private fun getTodayTimeTable() = intent {
        viewModelScope.launch {
            getTimeTableUseCase()
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
    }

    private fun getTodayMeals() = intent {
        viewModelScope.launch {
            getMealsUseCase()
                .onSuccess { response ->
                    reduce {
                        state.copy(
                            targetDate = response.first,
                            breakfast = response.second.breakfast,
                            lunch = response.second.lunch,
                            dinner = response.second.dinner
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
}