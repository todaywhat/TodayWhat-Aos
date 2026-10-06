package khs.onmi.enterinformation.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.onmi.domain.model.user.UserInfoModel
import com.onmi.domain.usecase.school.GetSchoolDepartmentsUseCase
import com.onmi.domain.usecase.school.SearchSchoolByNameUseCase
import com.onmi.domain.usecase.user.UpdateUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.core.common.android.EventLogger
import khs.onmi.enterinformation.model.School
import khs.onmi.enterinformation.viewmodel.container.EnterInformationSideEffect
import khs.onmi.enterinformation.viewmodel.container.EnterInformationState
import khs.onmi.navigation.ONMINavRoutes
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EnterInformationViewModel @Inject constructor(
    private val searchSchoolByNameUseCase: SearchSchoolByNameUseCase,
    private val getSchoolDepartmentsUseCase: GetSchoolDepartmentsUseCase,
    private val updateUserInfoUseCase: UpdateUserInfoUseCase,
) : ContainerHost<EnterInformationState, EnterInformationSideEffect>, ViewModel() {
    override val container =
        container<EnterInformationState, EnterInformationSideEffect>(EnterInformationState())

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

    fun saveEnteredUserInfo() = intent {
        val selectedSchool = state.selectedSchool
        val userInfo = UserInfoModel(
            schoolCode = selectedSchool?.schoolCode.orEmpty(),
            educationCode = selectedSchool?.educationCode.orEmpty(),
            schoolName = state.school,
            schoolType = selectedSchool?.schoolType.orEmpty(),
            grade = state.grade.toInt(),
            classroom = state.`class`.toInt(),
            department = state.department,
        )
        runCatching {
            updateUserInfoUseCase(userInfo)
        }.onSuccess {
            EventLogger.setUserProperties(
                "school_type" to userInfo.schoolType,
                "is_skip_weekend" to "false",
                "is_custom_time_table" to "false",
                "is_skip_after_dinner" to "false",
            )
            postSideEffect(EnterInformationSideEffect.Navigate(ONMINavRoutes.MAIN))
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

    fun onSchoolValueChange(school: String) = blockingIntent {
        reduce {
            state.copy(school = school)
        }
    }

    fun onGradeValueChange(grade: String) {
        if (grade.isDigitsOnly()) {
            blockingIntent {
                reduce {
                    state.copy(grade = grade)
                }
            }
        }
    }

    fun onClassValueChange(`class`: String) {
        if (`class`.isDigitsOnly()) {
            blockingIntent {
                reduce {
                    state.copy(`class` = `class`)
                }
            }
        }
    }

    fun onDepartmentValueChange(department: String) = blockingIntent {
        reduce {
            state.copy(department = department)
        }
    }
}