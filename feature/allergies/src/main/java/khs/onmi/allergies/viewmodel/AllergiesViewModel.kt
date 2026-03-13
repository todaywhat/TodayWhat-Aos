package khs.onmi.allergies.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.allergies.viewmodel.container.AllergiesSideEffect
import khs.onmi.allergies.viewmodel.container.AllergiesState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AllergiesViewModel @Inject constructor() :
    ContainerHost<AllergiesState, AllergiesSideEffect>, ViewModel() {

    override val container =
        container<AllergiesState, AllergiesSideEffect>(AllergiesState())
}
