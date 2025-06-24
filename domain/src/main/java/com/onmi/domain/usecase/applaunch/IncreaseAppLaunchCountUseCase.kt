package com.onmi.domain.usecase.applaunch

import com.onmi.domain.repository.AppLaunchRepository
import javax.inject.Inject

class IncreaseAppLaunchCountUseCase @Inject constructor(
    private val appLaunchRepository: AppLaunchRepository,
) {
    suspend operator fun invoke() {
        appLaunchRepository.increaseAppLaunchCount()
    }
}