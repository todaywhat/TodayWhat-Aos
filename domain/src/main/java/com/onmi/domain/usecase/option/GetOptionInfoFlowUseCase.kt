package com.onmi.domain.usecase.option

import com.onmi.domain.model.option.OptionInfoModel
import com.onmi.domain.repository.LocalOptionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOptionInfoFlowUseCase @Inject constructor(
    private val localOptionRepository: LocalOptionRepository,
) {
    operator fun invoke(): Flow<OptionInfoModel> = localOptionRepository.getOptionInfo()
}