package com.onmi.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ONMIDao {
    @Query("SELECT * FROM user_table")
    fun getUserInfo(): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserInfo(userEntity: UserEntity)

    @Delete
    fun deleteUserInfo(userEntity: UserEntity)
}