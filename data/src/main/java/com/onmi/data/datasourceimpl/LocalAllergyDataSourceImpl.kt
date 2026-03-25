package com.onmi.data.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.onmi.data.datasource.LocalAllergyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalAllergyDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : LocalAllergyDataSource {

    companion object {
        private val SELECTED_ALLERGY_IDS = stringSetPreferencesKey("selected_allergy_ids")
    }

    override fun getSelectedAllergyIds(): Flow<Set<Int>> {
        return dataStore.data.map { prefs ->
            prefs[SELECTED_ALLERGY_IDS]?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
        }
    }

    override suspend fun saveSelectedAllergyIds(ids: Set<Int>) {
        dataStore.edit { prefs ->
            prefs[SELECTED_ALLERGY_IDS] = ids.map { it.toString() }.toSet()
        }
    }
}
