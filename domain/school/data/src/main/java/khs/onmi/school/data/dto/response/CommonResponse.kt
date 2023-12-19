package khs.onmi.school.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse<T>(
    @SerialName("head")
    val head: List<Head> = emptyList(),
    @SerialName("row")
    val row: List<T> = emptyList(),
)

@Serializable
data class Head(
    @SerialName("list_total_count")
    val listTotalCount: Long = 0L,

    @SerialName("RESULT")
    val result: Result? = null,
)

@Serializable
data class Result(
    @SerialName("CODE")
    val code: String = "",

    @SerialName("MESSAGE")
    val message: String = "",
)