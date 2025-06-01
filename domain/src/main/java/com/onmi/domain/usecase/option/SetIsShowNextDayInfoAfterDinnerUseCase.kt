package com.onmi.domain.usecase.option

import com.onmi.domain.repository.LocalOptionRepository
import javax.inject.Inject

class SetIsShowNextDayInfoAfterDinnerUseCase @Inject constructor(
    private val localOptionRepository: LocalOptionRepository,
) {
    suspend operator fun invoke(isShowNextDayInfoAfterDinner: Boolean) {
        localOptionRepository.setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner = isShowNextDayInfoAfterDinner)
    }
}