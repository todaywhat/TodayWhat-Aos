package com.onmi.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface BaseResponse {
    val result: ErrorResult?
}

@Serializable
data class ErrorResult(
    @SerialName("CODE")
    val code: String,
    @SerialName("MESSAGE")
    val message: String,
)
