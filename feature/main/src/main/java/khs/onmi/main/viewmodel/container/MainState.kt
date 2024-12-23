package khs.onmi.main.viewmodel.container

data class MainState(
    val schoolName: String = "",
    val grade: Int = 0,
    val `class`: Int = 0,
    val targetDate: String = "",
    val breakfast: Pair<List<String>, String> = Pair(emptyList(), ""),
    val lunch: Pair<List<String>, String> = Pair(emptyList(), ""),
    val dinner: Pair<List<String>, String> = Pair(emptyList(), ""),
    val timetable: List<String> = emptyList(),
)