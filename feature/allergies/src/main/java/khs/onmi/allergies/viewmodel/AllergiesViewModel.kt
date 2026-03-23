package khs.onmi.allergies.viewmodel

import androidx.lifecycle.ViewModel
import com.onmi.domain.repository.AllergyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.allergies.viewmodel.container.AllergiesSideEffect
import khs.onmi.allergies.viewmodel.container.AllergiesState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AllergiesViewModel @Inject constructor(
    private val allergyRepository: AllergyRepository,
) : ContainerHost<AllergiesState, AllergiesSideEffect>, ViewModel() {

    override val container =
        container<AllergiesState, AllergiesSideEffect>(AllergiesState())

    init {
        loadSavedAllergies()
    }

    private fun loadSavedAllergies() = intent {
        allergyRepository.getSelectedAllergyIds().collect { savedIds ->
            reduce { state.copy(selectedAllergyIds = savedIds) }
        }
    }

    fun toggleAllergy(id: Int) = intent {
        reduce {
            val updatedIds = if (id in state.selectedAllergyIds) {
                state.selectedAllergyIds - id
            } else {
                state.selectedAllergyIds + id
            }

            state.copy(selectedAllergyIds = updatedIds)
        }
        allergyRepository.saveSelectedAllergyIds(state.selectedAllergyIds)
    }
}
