package khs.onmi.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onmi.database.UserDao
import com.onmi.domain.usecase.common.CalculateTargetDateUseCase
import com.onmi.domain.usecase.meal.GetMealsUseCase
import com.onmi.domain.usecase.meal.MealState
import com.onmi.domain.usecase.timetable.GetTimeTableUseCase
import com.onmi.domain.usecase.timetable.TimeTableState
import com.onmi.domain.util.DateUtils
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
    private val calculateTargetDateUseCase: CalculateTargetDateUseCase,
    private val getTimeTableUseCase: GetTimeTableUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val onmiDao: UserDao,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container = container<MainState, MainSideEffect>(MainState())
    private var targetDate = ""

    init {
        getUserInfo()
        getTargetDate()
    }

    private fun settingMainScreen() {
        if (targetDate.isNotEmpty()) {
            getTodayMeals()
            getTodayTimeTable()
        }
    }

    private fun getTargetDate() = intent {
        calculateTargetDateUseCase()
            .onSuccess { targetDate ->
                this@MainViewModel.targetDate = targetDate
                reduce {
                    state.copy(targetDate = DateUtils.convertToMonthDay(targetDate))
                }

                settingMainScreen()
            }.onFailure {
                postSideEffect(MainSideEffect.ShowToast("메인화면 정보를 가져오는데 문제가 발생했습니다."))
            }
    }

    private fun getUserInfo() = intent {
        kotlin.runCatching {
            onmiDao.getUserInfo()
        }.onSuccess {
            it.collectLatest { userEntity ->
                if (userEntity != null) {
                    getTargetDate()
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

    fun getTodayTimeTable() = intent {
        reduce {
            state.copy(timeTableState = TimeTableState.Loading)
        }

        viewModelScope.launch {
            val response = getTimeTableUseCase(targetDate = targetDate)

            reduce {
                state.copy(timeTableState = response)
            }
        }
    }

    fun getTodayMeals() = intent {
        reduce {
            state.copy(mealState = MealState.Loading)
        }

        viewModelScope.launch {
            val response = getMealsUseCase(targetDate = targetDate)

            reduce {
                state.copy(mealState = response)
            }
        }
    }
}