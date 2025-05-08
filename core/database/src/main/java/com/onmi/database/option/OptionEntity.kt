package com.onmi.database.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "option_table")
data class OptionEntity(
    @PrimaryKey
    var uuid: UUID = UUID.randomUUID(),
    var isSkipWeekend: Boolean = false,
    var isShowNextDayInfoAfterDinner: Boolean = false,
)
