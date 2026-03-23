package com.onmi.data.repository

import com.onmi.data.datasource.LocalAllergyDataSource
import com.onmi.domain.repository.AllergyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllergyRepositoryImpl @Inject constructor(
    private val dataSource: LocalAllergyDataSource,
) : AllergyRepository {

    override fun getSelectedAllergyIds(): Flow<List<Int>> {
        return dataSource.getSelectedAllergyIds()
    }

    override suspend fun saveSelectedAllergyIds(ids: List<Int>) {
        dataSource.saveSelectedAllergyIds(ids)
    }
}
