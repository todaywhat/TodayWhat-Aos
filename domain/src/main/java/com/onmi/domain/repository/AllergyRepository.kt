package com.onmi.domain.repository

import kotlinx.coroutines.flow.Flow

interface AllergyRepository {
    fun getSelectedAllergyIds(): Flow<Set<Int>>
    suspend fun saveSelectedAllergyIds(ids: Set<Int>)
}
