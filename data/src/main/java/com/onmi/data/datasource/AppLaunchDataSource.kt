package com.onmi.data.datasource

import kotlinx.coroutines.flow.Flow

interface AppLaunchDataSource {
    fun getAppLaunchCount(): Flow<Int>

    suspend fun increaseAppLaunchCount()
}