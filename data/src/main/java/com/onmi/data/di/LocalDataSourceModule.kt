package com.onmi.data.di

import com.onmi.data.datasource.LocalOptionDataSource
import com.onmi.data.datasource.LocalUserDataSource
import com.onmi.data.datasourceimpl.LocalOptionDataSourceImpl
import com.onmi.data.datasourceimpl.LocalUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun bindLocalOptionDataSource(
        impl: LocalOptionDataSourceImpl,
    ): LocalOptionDataSource

    @Binds
    @Singleton
    fun bindLocalUserDataSource(
        impl: LocalUserDataSourceImpl,
    ): LocalUserDataSource
}