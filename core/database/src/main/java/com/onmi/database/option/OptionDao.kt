package com.onmi.database.option

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDao {
    @Query("SELECT * FROM option_table")
    fun getOptionInfo(): Flow<OptionEntity?>

    @Query("UPDATE option_table SET isSkipWeekend = :isSkipWeekend")
    suspend fun setIsSkipWeekend(isSkipWeekend: Boolean)

    @Query("UPDATE option_table SET isShowNextDayInfoAfterDinner = :isShowNextDayInfoAfterDinner")
    suspend fun setIsShowNextDayInfoAfterDinner(isShowNextDayInfoAfterDinner: Boolean)
}