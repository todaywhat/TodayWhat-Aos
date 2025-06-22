package com.onmi.data.repository

import com.onmi.data.datasource.LocalOptionDataSource
import com.onmi.data.dto.option.toDomain
import com.onmi.domain.model.option.OptionInfoModel
import com.onmi.domain.repository.LocalOptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalOptionRepositoryImpl @Inject constructor(
    private val dataSource: LocalOptionDataSource,
) : LocalOptionRepository {
    override fun getOptionInfo(): Flow<OptionInfoModel> {
        return dataSource.getOptionInfo().map { it.toDomain() }
    }

    override suspend fun setIsSkipWeekend(isSkipWeekend: Boolean) {
        dataSource.setIsSkipWeekend(isSkipWeekend)
    }

    override suspend fun setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner: Boolean) {
        dataSource.setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner)
    }

}