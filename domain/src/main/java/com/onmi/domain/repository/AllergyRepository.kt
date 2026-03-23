package com.onmi.domain.repository

import kotlinx.coroutines.flow.Flow

interface AllergyRepository {
    fun getSelectedAllergyIds(): Flow<List<Int>>
    suspend fun saveSelectedAllergyIds(ids: List<Int>)
}
