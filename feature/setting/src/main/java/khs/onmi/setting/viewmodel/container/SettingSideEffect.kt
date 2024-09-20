package khs.onmi.setting.viewmodel.container

sealed class SettingSideEffect {
    data class ShowToast(val message: String) : SettingSideEffect()
}