package com.onmi.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 3, to = 4)]
)
abstract class ONMIDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(context: Context): ONMIDatabase = Room
            .databaseBuilder(context, ONMIDatabase::class.java, "onmi.db")
            .build()
    }
}