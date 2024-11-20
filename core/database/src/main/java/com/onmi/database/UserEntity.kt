package com.onmi.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    var schoolCode: String = "",
    var educationCode: String = "",
    var schoolName: String = "",
    @ColumnInfo(defaultValue = "")
    var schoolType: String,
    var grade: Int = 0,
    var classroom: Int = 0,
    var department: String = "",
    @ColumnInfo(name = "isSkipWeekend", defaultValue = "false")
    var isSkipWeekend: Boolean = false,
    @ColumnInfo(name = "isShowNextDayInfoAfterDinner", defaultValue = "false")
    var isShowNextDayInfoAfterDinner: Boolean = false
)
