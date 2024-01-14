package khs.onmi.main.viewmodel.container

sealed class MainSideEffect {
    data class ShowToast(val message: String) : MainSideEffect()
}