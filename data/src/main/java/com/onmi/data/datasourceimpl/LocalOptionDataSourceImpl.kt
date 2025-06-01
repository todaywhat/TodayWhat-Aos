package com.onmi.data.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.onmi.data.datasource.LocalOptionDataSource
import com.onmi.data.dto.option.OptionInfoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalOptionDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : LocalOptionDataSource {
    companion object {
        private val isSkipWeekendPreferencesKey =
            booleanPreferencesKey("isSkipWeekend")
        private val isShowNextDayInfoAfterDinnerPreferencesKey =
            booleanPreferencesKey("isShowNextDayInfoAfterDinner")
    }

    override fun getOptionInfo(): Flow<OptionInfoDto> {
        return dataStore.data.map { data ->
            OptionInfoDto(
                isSkipWeekend = data[isSkipWeekendPreferencesKey] ?: false,
                isShowNextDayInfoAfterDinner = data[isShowNextDayInfoAfterDinnerPreferencesKey] ?: false
            )
        }
    }

    override suspend fun setIsSkipWeekend(isSkipWeekend: Boolean) {
        dataStore.edit {
            it[isSkipWeekendPreferencesKey] = isSkipWeekend
        }
    }

    override suspend fun setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner: Boolean) {
        dataStore.edit {
            it[isShowNextDayInfoAfterDinnerPreferencesKey] = isShowNextDayInfoAfterDinner
        }
    }
}