package khs.onmi.enterinformation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.enterinformation.model.CurrentStage
import khs.onmi.enterinformation.viewmodel.container.EnterInformationSideEffect
import khs.onmi.enterinformation.viewmodel.container.EnterInformationState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EnterInformationViewModel @Inject constructor(

) : ContainerHost<EnterInformationState, EnterInformationSideEffect>, ViewModel() {

    override val container = container<EnterInformationState, EnterInformationSideEffect>(
        EnterInformationState()
    )

    init {
        setDummy()
    }

    private fun setDummy() = intent {
        reduce {
            state.copy(
                schoolList = listOf(
                    Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
                    Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
                    Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
                    Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
                    Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
                    Pair("광주소프트웨어마이스터고", "광주광역시 광산구"),
                ),
                departmentList = listOf(
                    "없음",
                    "SW 개발과",
                    "임베디드 개발과",
                    "스마트IoT과",
                    "임베디드SW과",
                    "e-비즈니스과",
                ),
            )
        }
    }

    fun setCurrentStage(currentStage: CurrentStage) = intent {
        reduce {
            state.copy(currentStage = currentStage)
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