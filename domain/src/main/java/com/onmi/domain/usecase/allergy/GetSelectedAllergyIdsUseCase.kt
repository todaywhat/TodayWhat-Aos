package com.onmi.domain.usecase.allergy

import com.onmi.domain.repository.AllergyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedAllergyIdsUseCase @Inject constructor(
    private val allergyRepository: AllergyRepository,
) {
    operator fun invoke(): Flow<List<Int>> = allergyRepository.getSelectedAllergyIds()
}
