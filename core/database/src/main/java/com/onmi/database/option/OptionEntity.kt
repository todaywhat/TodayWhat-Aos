package com.onmi.database.option

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "option_table")
data class OptionEntity(
    @PrimaryKey
    var primaryKey: String = "primaryKey",
    var isSkipWeekend: Boolean = false,
    var isShowNextDayInfoAfterDinner: Boolean = false,
)
