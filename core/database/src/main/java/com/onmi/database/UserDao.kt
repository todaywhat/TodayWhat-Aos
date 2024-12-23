package com.onmi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getUserInfo(): Flow<UserEntity?>

    @Transaction
    suspend fun replaceUserInfo(userEntity: UserEntity) {
        clearUserTable()
        insertUserInfo(userEntity = userEntity)
    }

    @Query("DELETE FROM user_table")
    suspend fun clearUserTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userEntity: UserEntity)

    @Query("UPDATE user_table SET isSkipWeekend = :isSkipWeekend")
    suspend fun setIsSkipWeekend(isSkipWeekend: Boolean)

    @Query("UPDATE user_table SET isShowNextDayInfoAfterDinner = :isShowNextDayInfoAfterDinner")
    suspend fun setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner: Boolean)
}