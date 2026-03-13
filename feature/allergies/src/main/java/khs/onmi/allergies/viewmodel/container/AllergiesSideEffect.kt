package khs.onmi.allergies.viewmodel.container

sealed class AllergiesSideEffect {
    data class ShowToast(val message: String) : AllergiesSideEffect()
}
