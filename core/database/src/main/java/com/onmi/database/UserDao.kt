package com.onmi.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    suspend fun getUserInfo(): UserEntity

    @Upsert
    suspend fun upsertUserInfo(userEntity: UserEntity)

    @Delete
    suspend fun deleteUserInfo(userEntity: UserEntity)
}