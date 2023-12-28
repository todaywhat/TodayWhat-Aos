package com.onmi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    val schoolCode: String = "",
    val educationCode: String = "",
    val schoolName: String = "",
    val grade: Int = 0,
    val `class`: Int = 0,
    val department: String = "",
)
