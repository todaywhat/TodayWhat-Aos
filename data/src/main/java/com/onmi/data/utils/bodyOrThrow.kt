package com.onmi.data.utils

import com.onmi.data.dto.BaseResponse
import com.onmi.domain.exception.NeisException
import com.onmi.domain.exception.NeisResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T : BaseResponse> HttpResponse.bodyOrThrow(): T {
    val responseBody = this.body<T>()

    if (responseBody.result != null) {
        val code = responseBody.result!!.code
        throw NeisException(NeisResult.find(code))
    }

    return responseBody
}