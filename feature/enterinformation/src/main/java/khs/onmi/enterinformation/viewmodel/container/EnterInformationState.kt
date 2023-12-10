package khs.onmi.enterinformation.viewmodel.container

import khs.onmi.enterinformation.model.CurrentStage

data class EnterInformationState(
    val schoolList: List<Pair<String, String>> = emptyList(),
    val departmentList: List<String> = emptyList(),
    val school: String = "",
    val grade: String = "",
    val `class`: String = "",
    val department: String = "",
    val schoolSelectorVisible: Boolean = false,
    val departmentSelectorVisible: Boolean = false,
    val currentStage: CurrentStage = CurrentStage.ENTERSCHOOL,
    val greetingTitle: String = "",
    val greetingBody: String = "",
)