package com.onmi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ONMIDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(context: Context): ONMIDatabase = Room
            .databaseBuilder(context, ONMIDatabase::class.java, "onmi.db")
            .build()
    }
}