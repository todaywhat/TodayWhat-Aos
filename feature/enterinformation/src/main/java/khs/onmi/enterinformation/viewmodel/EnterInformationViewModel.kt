package khs.onmi.enterinformation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.onmi.database.user.UserDao
import com.onmi.database.user.UserEntity
import com.onmi.domain.usecase.school.GetSchoolDepartmentsUseCase
import com.onmi.domain.usecase.school.SearchSchoolByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.core.common.android.EventLogger
import khs.onmi.enterinformation.model.CurrentState
import khs.onmi.enterinformation.model.School
import khs.onmi.enterinformation.viewmodel.container.EnterInformationSideEffect
import khs.onmi.enterinformation.viewmodel.container.EnterInformationState
import khs.onmi.navigation.ONMINavRoutes
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EnterInformationViewModel @Inject constructor(
    private val searchSchoolByNameUseCase: SearchSchoolByNameUseCase,
    private val getSchoolDepartmentsUseCase: GetSchoolDepartmentsUseCase,
    private val userDao: UserDao,
) : ContainerHost<EnterInformationState, EnterInformationSideEffect>, ViewModel() {
    override val container =
        container<EnterInformationState, EnterInformationSideEffect>(EnterInformationState())

    var school by mutableStateOf("")
        private set
    var grade by mutableStateOf("")
        private set
    var `class` by mutableStateOf("")
        private set
    var department by mutableStateOf("")
        private set

    init {
        searchSchoolByName()
    }

    fun searchSchoolByName(school: String = "") = intent {
        searchSchoolByNameUseCase(
            searchKeyword = school
        ).onSuccess {
            reduce {
                state.copy(schoolList = it.map { school ->
                    School(
                        schoolCode = school.schoolCode,
                        educationCode = school.educationCode,
                        schoolName = school.schoolName,
                        schoolLocation = school.schoolLocation,
                        schoolType = school.schoolType,
                    )
                })
            }
        }
    }

    fun getSchoolDepartments(
        educationCode: String,
        schoolCode: String,
    ) = intent {
        getSchoolDepartmentsUseCase(
            educationCode = educationCode,
            schoolCode = schoolCode,
        ).onSuccess {
            reduce {
                state.copy(departmentList = it.map { it.department })
            }
        }.onFailure {
            reduce {
                state.copy(departmentList = emptyList())
            }
        }
    }

    fun saveEnteredUserInfo(
        schoolCode: String,
        educationCode: String,
        schoolName: String,
        schoolType: String,
        grade: Int,
        `class`: Int,
        department: String,
    ) = intent {
        kotlin.runCatching {
            userDao.replaceUserInfo(
                UserEntity(
                    schoolCode = schoolCode,
                    educationCode = educationCode,
                    schoolName = schoolName,
                    schoolType = schoolType,
                    grade = grade,
                    classroom = `class`,
                    department = department,
                )
            )
        }.onSuccess {
            EventLogger.setUserProperties(
                "school_type" to schoolType,
                "is_skip_weekend" to "false",
                "is_custom_time_table" to "false",
                "is_skip_after_dinner" to "false",
            )
            postSideEffect(EnterInformationSideEffect.Navigate(ONMINavRoutes.MAIN))
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

    fun onSchoolValueChange(school: String) {
        this.school = school
    }

    fun onGradeValueChange(grade: String) {
        if (grade.isDigitsOnly()) {
            this.grade = grade
        }
    }

    fun onClassValueChange(`class`: String) {
        if (`class`.isDigitsOnly()) {
            this.`class` = `class`
        }
    }

    fun onDepartmentValueChange(department: String) {
        this.department = department
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