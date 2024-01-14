package khs.onmi.root

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onmi.database.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDao: UserDao,
) : ViewModel() {
    fun checkUserAlreadyEnteredInfo(action: (isEntered: Boolean) -> Unit) = viewModelScope.launch {
        kotlin.runCatching {
            userDao.getUserInfo()
        }.onSuccess {
            action(it != null)
        }.onFailure {
            Log.d("logtag", it.toString())
        }
    }
}