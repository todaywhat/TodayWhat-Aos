package khs.onmi.main.viewmodel.container

import com.onmi.domain.usecase.meal.MealState
import com.onmi.domain.usecase.timetable.TimeTableState

data class MainState(
    val schoolName: String = "",
    val grade: Int = 0,
    val `class`: Int = 0,
    val targetDate: String = "",
    val mealState: MealState = MealState.Loading,
    val timeTableState: TimeTableState = TimeTableState.Loading,
)