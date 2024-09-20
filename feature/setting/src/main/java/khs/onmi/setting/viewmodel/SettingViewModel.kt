package khs.onmi.setting.viewmodel

import androidx.lifecycle.ViewModel
import com.onmi.database.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.setting.viewmodel.container.SettingSideEffect
import khs.onmi.setting.viewmodel.container.SettingState
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
                postSideEffect(SettingSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
            }
        }.onFailure {
            postSideEffect(SettingSideEffect.ShowToast("사용자 정보를 가져오는데 실패했습니다."))
        }
    }
}