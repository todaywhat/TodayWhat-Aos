package com.onmi.data.di

import com.onmi.data.repository.AppLaunchRepositoryImpl
import com.onmi.data.repository.LocalOptionRepositoryImpl
import com.onmi.data.repository.LocalUserRepositoryImpl
import com.onmi.data.repository.MealRepositoryImpl
import com.onmi.data.repository.SchoolRepositoryImpl
import com.onmi.data.repository.TimeTableRepositoryImpl
import com.onmi.domain.repository.AppLaunchRepository
import com.onmi.domain.repository.LocalOptionRepository
import com.onmi.domain.repository.LocalUserRepository
import com.onmi.domain.repository.MealRepository
import com.onmi.domain.repository.SchoolRepository
import com.onmi.domain.repository.TimeTableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindMealRepository(
        impl: MealRepositoryImpl,
    ): MealRepository

    @Singleton
    @Binds
    fun bindSchoolRepository(
        impl: SchoolRepositoryImpl,
    ): SchoolRepository

    @Singleton
    @Binds
    fun bindTimeTableRepository(
        impl: TimeTableRepositoryImpl,
    ): TimeTableRepository

    @Singleton
    @Binds
    fun bindLocalUserRepository(
        impl: LocalUserRepositoryImpl,
    ): LocalUserRepository

    @Singleton
    @Binds
    fun bindLocalOptionRepository(
        impl: LocalOptionRepositoryImpl,
    ): LocalOptionRepository

    @Singleton
    @Binds
    fun bindAppLaunchRepository(
        impl: AppLaunchRepositoryImpl,
    ): AppLaunchRepository
}