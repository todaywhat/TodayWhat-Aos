package khs.onmi.enterinformation.viewmodel.container

import khs.onmi.enterinformation.model.CurrentState
import khs.onmi.enterinformation.model.School

data class EnterInformationState(
    val schoolList: List<School> = emptyList(),
    val departmentList: List<String> = emptyList(),
    val schoolSelectorVisible: Boolean = false,
    val departmentSelectorVisible: Boolean = false,
    val school: String = "",
    val grade: String = "",
    val `class`: String = "",
    val department: String = "",
) {
    val currentState: CurrentState
        get() = when {
            school.isBlank() || schoolSelectorVisible -> CurrentState.ENTERSCHOOL
            grade.isEmpty() -> CurrentState.ENTERGRADE
            `class`.isEmpty() -> CurrentState.ENTERCLASS
            department.isEmpty() -> CurrentState.ENTERDEPARTMENT
            else -> CurrentState.FINISH
        }

    val greetings: Pair<String, String>
        get() = when (currentState) {
            CurrentState.ENTERSCHOOL -> Pair("", "")
            CurrentState.ENTERGRADE -> Pair("몇학년 이신가요?", "")
            CurrentState.ENTERCLASS -> Pair("몇반 이신가요?", "")
            CurrentState.ENTERDEPARTMENT -> Pair("특정 학과에 다니시나요?", "학과는 없으면 안해도 괜찮아요!")
            CurrentState.FINISH -> Pair("입력하신 정보가 정확한가요?", "정보는 설정에서 언제든지 변경할 수 있어요.")
        }

    val selectedSchool: School?
        get() = schoolList.find { it.schoolName == school }
}