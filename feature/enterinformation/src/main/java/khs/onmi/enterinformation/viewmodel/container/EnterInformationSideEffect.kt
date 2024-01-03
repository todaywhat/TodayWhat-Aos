package khs.onmi.enterinformation.viewmodel.container

sealed class EnterInformationSideEffect {
    data class Navigate(val route: String) : EnterInformationSideEffect()
}