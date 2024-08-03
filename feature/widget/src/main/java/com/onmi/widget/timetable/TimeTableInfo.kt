package com.onmi.widget.timetable

import kotlinx.serialization.Serializable

@Serializable
sealed interface TimeTableInfo {
    @Serializable
    object Loading : TimeTableInfo

    @Serializable
    data class Available(
        val timeTableData: List<String>
    ) : TimeTableInfo

    @Serializable
    object Unavailable : TimeTableInfo
}