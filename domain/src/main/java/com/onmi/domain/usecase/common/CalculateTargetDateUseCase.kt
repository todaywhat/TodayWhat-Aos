package com.onmi.domain.usecase.common

import com.onmi.domain.repository.LocalOptionRepository
import com.onmi.domain.util.DateUtils
import com.onmi.domain.util.DateUtils.convertMillisToDateString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalculateTargetDateUseCase @Inject constructor(
    private val optionRepository: LocalOptionRepository,
) {
    operator fun invoke(): Flow<String> {
        return optionRepository.getOptionInfo().map { optionInfo ->
            when {
                DateUtils.checkIsWeekend() && optionInfo.isSkipWeekend -> DateUtils.getNextMondayDate()
                DateUtils.checkIsAfterDinner() && optionInfo.isShowNextDayInfoAfterDinner -> DateUtils.getNextDayDate()
                else -> convertMillisToDateString(System.currentTimeMillis())
            }
        }
    }
}