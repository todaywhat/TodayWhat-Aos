package com.onmi.domain.repository

import com.onmi.domain.model.user.UserInfoModel
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository {
    fun getUserInfo(): Flow<UserInfoModel>

    suspend fun clearUserInfo()

    suspend fun updateUserInfo(userInfo: UserInfoModel)
}