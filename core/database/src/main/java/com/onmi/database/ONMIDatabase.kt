package com.onmi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ONMIDatabase : RoomDatabase() {
    abstract fun onmiDao(): ONMIDao

    companion object {
        fun getInstance(context: Context): ONMIDatabase = Room
            .databaseBuilder(context, ONMIDatabase::class.java, "onmi.db")
            .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    Executors.newSingleThreadExecutor().execute {
                        runBlocking {
                            getInstance(context).onmiDao().saveUserInfo(userEntity = UserEntity())
                        }
                    }
                }
            }).build()
    }
}