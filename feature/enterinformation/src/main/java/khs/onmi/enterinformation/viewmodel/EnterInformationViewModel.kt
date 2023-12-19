package khs.onmi.enterinformation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.enterinformation.model.CurrentState
import khs.onmi.enterinformation.viewmodel.container.EnterInformationSideEffect
import khs.onmi.enterinformation.viewmodel.container.EnterInformationState
import khs.onmi.school.domain.usecase.SearchSchoolByNameUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EnterInformationViewModel @Inject constructor(
    private val searchSchoolByNameUseCase: SearchSchoolByNameUseCase,
) : ContainerHost<EnterInformationState, EnterInformationSideEffect>, ViewModel() {

    override val container = container<EnterInformationState, EnterInformationSideEffect>(
        EnterInformationState()
    )

    init {
        searchSchoolByName()
    }

    fun searchSchoolByName() = intent {
        searchSchoolByNameUseCase(
            searchKeyword = state.school
        ).onSuccess {
            reduce {
                state.copy(schoolList = it.map { Pair(it.schoolName, it.schoolLocation) })
            }
        }
    }

    fun setCurrentState(currentState: CurrentState) = intent {
        reduce {
            state.copy(currentState = currentState)
        }
    }

    fun setSchoolSelectorVisible(visible: Boolean) = intent {
        reduce {
            state.copy(schoolSelectorVisible = visible)
        }
    }

    fun setDepartmentSelectorVisible(visible: Boolean) = intent {
        reduce {
            state.copy(departmentSelectorVisible = visible)
        }
    }

    fun onSchoolValueChange(school: String) = intent {
        reduce {
            state.copy(school = school)
        }
    }

    fun onGradeValueChange(grade: String) = intent {
        reduce {
            state.copy(grade = grade)
        }
    }

    fun onClassValueChange(`class`: String) = intent {
        reduce {
            state.copy(`class` = `class`)
        }
    }

    fun onDepartmentValueChange(department: String) = intent {
        reduce {
            state.copy(department = department)
        }
    }

    fun onGreetingValueChange(greetings: Pair<String, String>) = intent {
        reduce {
            state.copy(
                greetingTitle = greetings.first,
                greetingBody = greetings.second,
            )
        }
    }
}