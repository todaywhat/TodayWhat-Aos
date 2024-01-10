package khs.onmi.main.viewmodel.container

data class MainState(
    val schoolName: String = "",
    val grade: Int = 0,
    val `class`: Int = 0,
    val breakfast: Pair<List<String>, Float> = Pair(emptyList(), 0F),
    val lunch: Pair<List<String>, Float> = Pair(emptyList(), 0F),
    val dinner: Pair<List<String>, Float> = Pair(emptyList(), 0F),
    val timetable: List<String> = emptyList(),
)