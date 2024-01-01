package com.onmi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    var schoolCode: String = "",
    var educationCode: String = "",
    var schoolName: String = "",
    var grade: Int = 0,
    var `class`: Int = 0,
    var department: String = "",
)
