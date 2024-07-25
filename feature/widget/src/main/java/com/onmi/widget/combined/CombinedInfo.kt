package com.onmi.widget.combined

import kotlinx.serialization.Serializable

@Serializable
sealed interface CombinedInfo {
    @Serializable
    data object Loading : CombinedInfo

    @Serializable
    data class Available(
        val mealTime: String,
        val mealList: List<String>,
        val subjectList: List<String>,
    ) : CombinedInfo

    @Serializable
    data object Unavailable : CombinedInfo
}