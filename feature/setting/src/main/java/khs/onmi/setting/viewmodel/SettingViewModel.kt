package khs.onmi.setting.viewmodel

import androidx.lifecycle.ViewModel
import com.onmi.database.option.OptionDao
import com.onmi.database.user.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.setting.viewmodel.container.SettingSideEffect
import khs.onmi.setting.viewmodel.container.SettingState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val userDao: UserDao,
    private val optionDao: OptionDao,
) : ContainerHost<SettingState, SettingSideEffect>, ViewModel() {

    override val container = container<SettingState, SettingSideEffect>(SettingState())

    init {
        getUserInfo()
        getOptionInfo()
    }

    private fun getUserInfo() = intent {
        userDao.getUserInfo()
            .catch {
                postSideEffect(SettingSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
            }.collect { userEntity ->
                if (userEntity != null) {
                    reduce {
                        state.copy(
                            schoolName = userEntity.schoolName,
                            grade = userEntity.grade,
                            `class` = userEntity.classroom,
                        )
                    }
                } else {
                    postSideEffect(SettingSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
                }
            }
    }

    private fun getOptionInfo() = intent {
        optionDao.getOptionInfo()
            .catch {
                postSideEffect(SettingSideEffect.ShowToast("옵션 정보를 가져오는데 실패했습니다."))
            }.collect { optionEntity ->
                if (optionEntity != null) {
                    reduce {
                        state.copy(
                            isSkipWeekend = optionEntity.isSkipWeekend,
                            isShowNextDayInfoAfterDinner = optionEntity.isShowNextDayInfoAfterDinner
                        )
                    }
                } else {
                    postSideEffect(SettingSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
                }
            }
    }

    fun setIsSkipWeekend(isSkipWeekend: Boolean) = intent {
        runCatching {
            optionDao.setIsSkipWeekend(isSkipWeekend = isSkipWeekend)
        }.onFailure {
            postSideEffect(SettingSideEffect.ShowToast("주말 건너뛰기 여부를 설정하지 못하였습니다."))
        }
    }

    fun setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner: Boolean) = intent {
        runCatching {
            optionDao.setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner = isShowNextDayInfoAfterDinner)
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