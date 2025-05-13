package com.onmi.data.repository

import com.onmi.data.datasource.LocalUserDataSource
import com.onmi.data.dto.user.toDto
import com.onmi.data.dto.user.toDomain
import com.onmi.domain.model.user.UserInfoModel
import com.onmi.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val dataSource: LocalUserDataSource,
) : LocalUserRepository {
    override fun getUserInfo(): Flow<UserInfoModel> {
        return dataSource.getUserInfo().map { it.toDomain() }
    }

    override suspend fun clearUserInfo() {
        dataSource.clearUserInfo()
    }

    override suspend fun updateUserInfo(userInfo: UserInfoModel) {
        dataSource.updateUserInfo(userInfo = userInfo.toDto())
    }
}