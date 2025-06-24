package com.onmi.domain.usecase.applaunch

import com.onmi.domain.repository.AppLaunchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppLaunchCountUseCase @Inject constructor(
    private val appLaunchRepository: AppLaunchRepository,
) {
    operator fun invoke(): Flow<Int> {
        return appLaunchRepository.getAppLaunchCount()
    }
}