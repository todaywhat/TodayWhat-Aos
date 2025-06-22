package com.onmi.data.datasource

import com.onmi.data.dto.option.OptionInfoDto
import kotlinx.coroutines.flow.Flow

interface LocalOptionDataSource {
    fun getOptionInfo(): Flow<OptionInfoDto>

    suspend fun setIsSkipWeekend(isSkipWeekend: Boolean)

    suspend fun setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner: Boolean)
}