package khs.onmi.setting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onmi.domain.usecase.option.GetOptionInfoFlowUseCase
import com.onmi.domain.usecase.option.SetIsShowNextDayInfoAfterDinnerUseCase
import com.onmi.domain.usecase.option.SetIsSkipWeekendUseCase
import com.onmi.domain.usecase.user.GetUserInfoFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.setting.viewmodel.container.SettingSideEffect
import khs.onmi.setting.viewmodel.container.SettingState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getUserInfoFlowUseCase: GetUserInfoFlowUseCase,
    private val getOptionInfoFlowUseCase: GetOptionInfoFlowUseCase,
    private val setIsSkipWeekendUseCase: SetIsSkipWeekendUseCase,
    private val setIsShowNextDayInfoAfterDinnerUseCase: SetIsShowNextDayInfoAfterDinnerUseCase,
) : ContainerHost<SettingState, SettingSideEffect>, ViewModel() {

    override val container = container<SettingState, SettingSideEffect>(SettingState())

    init {
        getUserInfo()
        getOptionInfo()
    }

    private fun getUserInfo() = viewModelScope.launch {
        getUserInfoFlowUseCase()
            .catch {
                intent {
                    postSideEffect(SettingSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
                }
            }.collect { userEntity ->
                intent {
                    reduce {
                        state.copy(
                            schoolName = userEntity.schoolName,
                            grade = userEntity.grade,
                            `class` = userEntity.classroom,
                        )
                    }
                }
            }
    }

    private fun getOptionInfo() = viewModelScope.launch {
        getOptionInfoFlowUseCase()
            .catch {
                intent {
                    postSideEffect(SettingSideEffect.ShowToast("옵션 정보를 가져오는데 실패했습니다."))
                }
            }.collect { optionInfo ->
                intent {
                    reduce {
                        state.copy(
                            isSkipWeekend = optionInfo.isSkipWeekend,
                            isShowNextDayInfoAfterDinner = optionInfo.isShowNextDayInfoAfterDinner
                        )
                    }
                }
            }
    }

    fun setIsSkipWeekend(isSkipWeekend: Boolean) = intent {
        runCatching {
            setIsSkipWeekendUseCase(isSkipWeekend = isSkipWeekend)
        }.onFailure {
            postSideEffect(SettingSideEffect.ShowToast("주말 건너뛰기 여부를 설정하지 못하였습니다."))
        }
    }

    fun setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner: Boolean) = intent {
        runCatching {
            setIsShowNextDayInfoAfterDinnerUseCase(isShowNextDayInfoAfterDinner = isShowNextDayInfoAfterDinner)
        }.onFailure {
            postSideEffect(SettingSideEffect.ShowToast("저녁 후 내일 급식 표시 여부를 설정하지 못하였습니다."))
        }
    }

    fun onSkipWeekendToggleValueChanged(value: Boolean) = intent {
        reduce {
            state.copy(isSkipWeekend = value)
        }
    }

    fun onShowNextDayInfoAfterDinnerValueChanged(value: Boolean) = intent {
        reduce {
            state.copy(isShowNextDayInfoAfterDinner = value)
        }
    }
}