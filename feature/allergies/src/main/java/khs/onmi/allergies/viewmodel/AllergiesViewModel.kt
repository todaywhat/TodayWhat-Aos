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
        val previousIds = state.selectedAllergyIds
        val updatedIds = if (id in previousIds) {
            previousIds - id
        } else {
            previousIds + id
        }

        reduce {
            state.copy(selectedAllergyIds = updatedIds)
        }
        runCatching {
            allergyRepository.saveSelectedAllergyIds(updatedIds)
        }.onFailure {
            reduce { state.copy(selectedAllergyIds = previousIds) }
            postSideEffect(AllergiesSideEffect.ShowToast("알레르기 저장에 실패했습니다."))
        }
    }
}
