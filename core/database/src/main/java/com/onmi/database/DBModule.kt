package com.onmi.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Singleton
    @Provides
    fun provideONMIDatabase(
        @ApplicationContext context: Context
    ): ONMIDatabase = ONMIDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideUserDao(database: ONMIDatabase): UserDao = database.userDao()
}