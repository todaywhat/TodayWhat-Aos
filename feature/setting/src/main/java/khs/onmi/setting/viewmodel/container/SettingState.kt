package khs.onmi.setting.viewmodel.container

data class SettingState(
    val schoolName: String = "",
    val grade: Int = 0,
    val `class`: Int = 0,
    val skipWeekend: Boolean = false,
)