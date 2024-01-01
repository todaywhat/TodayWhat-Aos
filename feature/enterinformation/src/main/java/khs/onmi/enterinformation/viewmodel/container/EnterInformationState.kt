package khs.onmi.enterinformation.viewmodel.container

import khs.onmi.enterinformation.model.CurrentState
import khs.onmi.enterinformation.model.School

data class EnterInformationState(
    val schoolList: List<School> = emptyList(),
    val departmentList: List<String> = emptyList(),
    val school: String = "",
    val grade: String = "",
    val `class`: String = "",
    val department: String = "",
    val schoolSelectorVisible: Boolean = false,
    val departmentSelectorVisible: Boolean = false,
    val currentState: CurrentState = CurrentState.ENTERSCHOOL,
    val greetingTitle: String = "",
    val greetingBody: String = "",
)