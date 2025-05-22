package com.onmi.domain.repository

import com.onmi.domain.model.option.OptionInfoModel
import kotlinx.coroutines.flow.Flow

interface LocalOptionRepository {
    fun getOptionInfo(): Flow<OptionInfoModel>

    suspend fun setIsSkipWeekend(isSkipWeekend: Boolean)

    suspend fun setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner: Boolean)
}