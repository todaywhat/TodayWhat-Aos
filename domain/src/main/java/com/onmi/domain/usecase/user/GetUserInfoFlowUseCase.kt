package com.onmi.domain.usecase.user

import com.onmi.domain.model.user.UserInfoModel
import com.onmi.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoFlowUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository,
) {
    operator fun invoke(): Flow<UserInfoModel> = localUserRepository.getUserInfo()
}