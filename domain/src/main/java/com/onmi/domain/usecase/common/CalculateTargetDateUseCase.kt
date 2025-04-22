package com.onmi.domain.usecase.common

import com.onmi.database.option.OptionDao
import com.onmi.domain.util.DateUtils
import com.onmi.domain.util.DateUtils.convertMillisToDateString
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/* TODO("이런 로직도 UseCase가 될 수 있을까?") */
class CalculateTargetDateUseCase @Inject constructor(
    private val optionDao: OptionDao,
) {
    suspend operator fun invoke(): Result<String> = kotlin.runCatching {
        val optionInfo = optionDao.getOptionInfo().first()
            ?: return@runCatching convertMillisToDateString(System.currentTimeMillis())

        return@runCatching when {
            DateUtils.checkIsWeekend() && optionInfo.isSkipWeekend -> DateUtils.getNextMondayDate()
            DateUtils.checkIsAfterDinner() && optionInfo.isShowNextDayInfoAfterDinner -> DateUtils.getNextDayDate()
            else -> convertMillisToDateString(System.currentTimeMillis())
        }
    }
}