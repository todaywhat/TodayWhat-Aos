package com.onmi.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.onmi.database.migration.Migration6To7
import com.onmi.database.migration.Migration7to8
import com.onmi.database.option.OptionDao
import com.onmi.database.option.OptionEntity
import com.onmi.database.user.UserDao
import com.onmi.database.user.UserEntity

@Database(
    entities = [UserEntity::class, OptionEntity::class],
    version = 8,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 5, to = 6),
    ]
)
abstract class ONMIDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun optionDao(): OptionDao

    companion object {
        fun getInstance(context: Context): ONMIDatabase = Room
            .databaseBuilder(context, ONMIDatabase::class.java, "onmi.db")
            .addMigrations(Migration6To7, Migration7to8)
            .build()
    }
}