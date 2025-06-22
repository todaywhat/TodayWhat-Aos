package com.onmi.data.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.onmi.data.datasource.LocalUserDataSource
import com.onmi.data.dto.user.UserInfoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : LocalUserDataSource {

    companion object {
        private val schoolCodePreferencesKey = stringPreferencesKey("schoolCode")
        private val educationCodePreferencesKey = stringPreferencesKey("educationCode")
        private val schoolNamePreferencesKey = stringPreferencesKey("schoolName")
        private val schoolTypePreferencesKey = stringPreferencesKey("schoolType")
        private val gradePreferencesKey = intPreferencesKey("grade")
        private val classroomPreferencesKey = intPreferencesKey("classroom")
        private val departmentPreferencesKey = stringPreferencesKey("department")
    }

    override fun getUserInfo(): Flow<UserInfoDto> {
        return dataStore.data.map { data ->
            UserInfoDto(
                schoolCode = data[schoolCodePreferencesKey] ?: "",
                educationCode = data[educationCodePreferencesKey] ?: "",
                schoolName = data[schoolNamePreferencesKey] ?: "",
                schoolType = data[schoolTypePreferencesKey] ?: "",
                grade = data[gradePreferencesKey] ?: 0,
                classroom = data[classroomPreferencesKey] ?: 0,
                department = data[departmentPreferencesKey] ?: ""
            )
        }
    }

    override suspend fun clearUserInfo() {
        updateUserInfo(
            UserInfoDto(
                schoolCode = "",
                educationCode = "",
                schoolName = "",
                schoolType = "",
                grade = 0,
                classroom = 0,
                department = ""
            )
        )
    }

    override suspend fun updateUserInfo(userInfo: UserInfoDto) {
        dataStore.edit {
            with(userInfo) {
                it[schoolCodePreferencesKey] = schoolCode
                it[educationCodePreferencesKey] = educationCode
                it[schoolNamePreferencesKey] = schoolName
                it[schoolTypePreferencesKey] = schoolType
                it[gradePreferencesKey] = grade
                it[classroomPreferencesKey] = classroom
                it[departmentPreferencesKey] = department
            }
        }
    }
}