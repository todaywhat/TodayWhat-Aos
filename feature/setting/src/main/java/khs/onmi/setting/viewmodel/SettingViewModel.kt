package khs.onmi.setting.viewmodel

import androidx.lifecycle.ViewModel
import com.onmi.database.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.setting.viewmodel.container.SettingSideEffect
import khs.onmi.setting.viewmodel.container.SettingState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val onmiDao: UserDao,
) : ContainerHost<SettingState, SettingSideEffect>, ViewModel() {

    override val container = container<SettingState, SettingSideEffect>(
        SettingState()
    )

    init {
        getUserInfo()
    }

    private fun getUserInfo() = intent {
        onmiDao.getUserInfo()
            .catch {
                postSideEffect(SettingSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
            }.collectLatest { userEntity ->
                if (userEntity != null) {
                    reduce {
                        state.copy(
                            schoolName = userEntity.schoolName,
                            grade = userEntity.grade,
                            `class` = userEntity.classroom,
                            isSkipWeekend = userEntity.isSkipWeekend
                        )
                    }
                } else {
                    postSideEffect(SettingSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
                }
            }
    }

    suspend fun setIsSkipWeekend(isSkipWeekend: Boolean) = intent {
        runCatching {
            onmiDao.setIsSkipWeekend(isSkipWeekend = isSkipWeekend)
        }.onFailure {
            postSideEffect(SettingSideEffect.ShowToast("주말 건너뛰기 여부를 설정하지 못하였습니다."))
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