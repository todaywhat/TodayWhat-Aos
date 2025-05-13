package com.onmi.domain.usecase.option

import com.onmi.domain.repository.LocalOptionRepository
import javax.inject.Inject

class SetIsSkipWeekendUseCase @Inject constructor(
    private val localOptionRepository: LocalOptionRepository,
) {
    suspend operator fun invoke(isSkipWeekend: Boolean) {
        localOptionRepository.setIsSkipWeekend(isSkipWeekend = isSkipWeekend)
    }
}