package khs.onmi.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onmi.domain.usecase.common.CalculateTargetDateUseCase
import com.onmi.domain.usecase.meal.GetMealsUseCase
import com.onmi.domain.usecase.meal.MealState
import com.onmi.domain.usecase.timetable.GetTimeTableUseCase
import com.onmi.domain.usecase.timetable.TimeTableState
import com.onmi.domain.usecase.user.GetUserInfoFlowUseCase
import com.onmi.domain.util.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.main.viewmodel.container.MainSideEffect
import khs.onmi.main.viewmodel.container.MainState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val calculateTargetDateUseCase: CalculateTargetDateUseCase,
    private val getTimeTableUseCase: GetTimeTableUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val getUserInfoFlowUseCase: GetUserInfoFlowUseCase,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container = container<MainState, MainSideEffect>(MainState())

    init {
        settingMainScreen()
    }

    private fun settingMainScreen() = viewModelScope.launch {
        combine(
            calculateTargetDateUseCase(),
            getUserInfoFlowUseCase()
        ) { targetDate, userInfo ->
            targetDate to userInfo
        }.catch {
            intent {
                postSideEffect(MainSideEffect.ShowToast("메인화면 정보를 가져오는데 문제가 발생했습니다."))
            }
        }.collect { (targetDate, userEntity) ->
            intent {
                reduce {
                    state.copy(
                        targetDate = DateUtils.convertToMonthDay(targetDate),
                        schoolName = userEntity.schoolName,
                        grade = userEntity.grade,
                        `class` = userEntity.classroom
                    )
                }
            }

            launch { getTodayTimeTable(targetDate = targetDate) }
            launch { getTodayMeals(targetDate = targetDate) }
        }
    }

    fun getTodayTimeTable(targetDate: String = "") = intent {
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

    fun getTodayMeals(targetDate: String = "") = intent {
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