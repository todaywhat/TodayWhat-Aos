package com.onmi.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppLaunchRepository {
    fun getAppLaunchCount(): Flow<Int>

    suspend fun increaseAppLaunchCount()
}