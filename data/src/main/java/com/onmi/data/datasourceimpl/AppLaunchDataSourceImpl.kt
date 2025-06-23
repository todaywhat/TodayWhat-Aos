package com.onmi.data.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.onmi.data.datasource.AppLaunchDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppLaunchDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : AppLaunchDataSource {
    companion object {
        private val appLaunchCountPreferencesKey = intPreferencesKey("appLaunchCount")
    }

    override fun getAppLaunchCount(): Flow<Int> {
        return dataStore.data.map { data ->
            data[appLaunchCountPreferencesKey] ?: 0
        }
    }

    override suspend fun increaseAppLaunchCount() {
        dataStore.edit {
            val currentCount = it[appLaunchCountPreferencesKey] ?: 0
            it[appLaunchCountPreferencesKey] = currentCount + 1
        }
    }
}