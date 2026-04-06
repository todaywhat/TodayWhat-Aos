package com.onmi.data.datasource

import kotlinx.coroutines.flow.Flow

interface LocalAllergyDataSource {
    fun getSelectedAllergyIds(): Flow<Set<Int>>
    suspend fun saveSelectedAllergyIds(ids: Set<Int>)
}
