package com.onmi.data.repository

import com.onmi.data.datasource.AppLaunchDataSource
import com.onmi.domain.repository.AppLaunchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppLaunchRepositoryImpl @Inject constructor(
    private val dataSource: AppLaunchDataSource,
) : AppLaunchRepository {
    override fun getAppLaunchCount(): Flow<Int> {
        return dataSource.getAppLaunchCount()
    }

    override suspend fun increaseAppLaunchCount() {
        dataSource.increaseAppLaunchCount()
    }
}