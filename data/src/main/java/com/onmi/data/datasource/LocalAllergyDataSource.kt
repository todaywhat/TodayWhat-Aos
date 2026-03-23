package com.onmi.data.datasource

import kotlinx.coroutines.flow.Flow

interface LocalAllergyDataSource {
    fun getSelectedAllergyIds(): Flow<List<Int>>
    suspend fun saveSelectedAllergyIds(ids: List<Int>)
}
