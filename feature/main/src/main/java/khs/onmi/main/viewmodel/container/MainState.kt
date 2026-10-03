package khs.onmi.main.viewmodel.container

import com.onmi.domain.usecase.meal.MealState
import com.onmi.domain.usecase.timetable.TimeTableState

data class MainState(
    val schoolName: String = "",
    val grade: Int = 0,
    val `class`: Int = 0,
    val targetDate: String = "",
    /* 실제 조회에 사용한 날짜(yyyyMMdd). 재시도·디버그 날짜 선택 시 기준으로 쓴다. */
    val rawTargetDate: String = "",
    /* 디버그 모드에서 강제 지정한 날짜(yyyyMMdd). 지정하지 않았으면 null */
    val debugTargetDate: String? = null,
    val mealState: MealState = MealState.Loading,
    val timeTableState: TimeTableState = TimeTableState.Loading,
    val selectedAllergyIds: Set<Int> = emptySet(),
)
