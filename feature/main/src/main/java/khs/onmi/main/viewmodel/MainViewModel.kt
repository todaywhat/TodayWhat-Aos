package khs.onmi.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onmi.domain.usecase.allergy.GetSelectedAllergyIdsUseCase
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
import kotlinx.coroutines.flow.first
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
    private val getSelectedAllergyIdsUseCase: GetSelectedAllergyIdsUseCase,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container = container<MainState, MainSideEffect>(MainState())

    init {
        settingMainScreen()
        collectAllergyIds()
    }

    private fun collectAllergyIds() = intent {
        getSelectedAllergyIdsUseCase()
            .catch {
                reduce { state.copy(selectedAllergyIds = emptySet()) }
            }
            .collect { ids ->
                reduce { state.copy(selectedAllergyIds = ids) }
            }
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
        }.collect { (calculatedDate, userEntity) ->
            /* 디버그 모드에서 날짜를 지정했다면 자동 계산 결과보다 우선한다. */
            val targetDate = container.stateFlow.value.debugTargetDate ?: calculatedDate

            intent {
                reduce {
                    state.copy(
                        targetDate = DateUtils.convertToMonthDay(targetDate),
                        rawTargetDate = targetDate,
                        schoolName = userEntity.schoolName,
                        grade = userEntity.grade,
                        `class` = userEntity.classroom
                    )
                }
            }

            getTodayTimeTable(targetDate = targetDate)
            getTodayMeals(targetDate = targetDate)
        }
    }

    fun reloadTimeTable() = intent {
        getTodayTimeTable(targetDate = state.rawTargetDate)
    }

    fun reloadMeals() = intent {
        getTodayMeals(targetDate = state.rawTargetDate)
    }

    /* 디버그 모드 전용. Compose DatePicker 가 돌려주는 UTC 자정 millis 를 받는다. */
    fun setDebugTargetDate(utcMillis: Long) =
        applyDebugTargetDate(DateUtils.convertUtcMillisToDateString(utcMillis))

    /* 디버그 모드 전용. 강제 지정한 날짜를 해제하고 원래 계산된 날짜로 되돌린다. */
    fun clearDebugTargetDate() = applyDebugTargetDate(null)

    private fun applyDebugTargetDate(date: String?) = intent {
        val targetDate = date ?: calculateTargetDateUseCase().first()

        reduce {
            state.copy(
                targetDate = DateUtils.convertToMonthDay(targetDate),
                rawTargetDate = targetDate,
                debugTargetDate = date
            )
        }

        getTodayTimeTable(targetDate = targetDate)
        getTodayMeals(targetDate = targetDate)
    }

    private fun getTodayTimeTable(targetDate: String) = intent {
        reduce {
            state.copy(timeTableState = TimeTableState.Loading)
        }

        val response = getTimeTableUseCase(targetDate = targetDate)

        reduce {
            state.copy(timeTableState = response)
        }
    }

    private fun getTodayMeals(targetDate: String) = intent {
        reduce {
            state.copy(mealState = MealState.Loading)
        }

        val response = getMealsUseCase(targetDate = targetDate)

        reduce {
            state.copy(mealState = response)
        }
    }
}
