package com.onmi.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    suspend fun getUserInfo(): UserEntity?

    @Transaction
    suspend fun replaceUserInfo(userEntity: UserEntity) {
        clearUserTable()
        insertUserInfo(userEntity = userEntity)
    }

    @Query("DELETE FROM user_table")
    suspend fun clearUserTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userEntity: UserEntity)
}