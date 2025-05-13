package com.onmi.data.datasource

import com.onmi.data.dto.user.UserInfoDto
import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {
    fun getUserInfo(): Flow<UserInfoDto>

    suspend fun clearUserInfo()

    suspend fun updateUserInfo(userInfo: UserInfoDto)
}