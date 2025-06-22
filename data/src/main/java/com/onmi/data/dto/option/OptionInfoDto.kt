package com.onmi.data.dto.option

import com.onmi.domain.model.option.OptionInfoModel

data class OptionInfoDto(
    var isSkipWeekend: Boolean,
    var isShowNextDayInfoAfterDinner: Boolean,
)

fun OptionInfoDto.toDomain() = OptionInfoModel(
    isSkipWeekend = isSkipWeekend,
    isShowNextDayInfoAfterDinner = isShowNextDayInfoAfterDinner
)