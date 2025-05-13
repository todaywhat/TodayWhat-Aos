package com.onmi.domain.usecase.user

import com.onmi.domain.model.user.UserInfoModel
import com.onmi.domain.repository.LocalUserRepository
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository,
) {
    suspend operator fun invoke(userInfo: UserInfoModel) {
        localUserRepository.updateUserInfo(userInfo = userInfo)
    }
}