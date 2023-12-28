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
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): ONMIDatabase = ONMIDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideAlbumDao(database: ONMIDatabase): ONMIDao = database.onmiDao()
}