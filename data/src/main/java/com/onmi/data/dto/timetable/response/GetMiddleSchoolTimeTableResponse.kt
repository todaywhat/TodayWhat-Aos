package com.onmi.data.dto.timetable.response

import com.onmi.data.dto.BaseResponse
import com.onmi.data.dto.CommonResponse
import com.onmi.data.dto.ErrorResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMiddleSchoolTimeTableResponse(
    @SerialName("RESULT")
    override val result: ErrorResult? = null,
    @SerialName("misTimetable")
    val timetable: List<CommonResponse<GetTimeTableResponse>>? = null,
) : BaseResponse
