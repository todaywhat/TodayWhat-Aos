package khs.onmi.root

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onmi.domain.usecase.user.GetUserInfoFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserInfoFlowUseCase: GetUserInfoFlowUseCase,
) : ViewModel() {
    fun checkUserAlreadyEnteredInfo(action: (isEntered: Boolean) -> Unit) = viewModelScope.launch {
        kotlin.runCatching {
            getUserInfoFlowUseCase().first()
        }.onSuccess {
            action(it.schoolCode.isNotEmpty())
        }.onFailure {
            Log.d("logtag", it.toString())
        }
    }
}