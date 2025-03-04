package com.onmi.data.dto

import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommonErrorResponse(
    @SerialName("RESULT")
    val result: Result,
) : IOException()
