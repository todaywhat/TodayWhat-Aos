package com.onmi.data.di

import com.onmi.data.repository.MealRepositoryImpl
import com.onmi.data.repository.SchoolRepositoryImpl
import com.onmi.data.repository.TimeTableRepositoryImpl
import com.onmi.domain.repository.MealRepository
import com.onmi.domain.repository.SchoolRepository
import com.onmi.domain.repository.TimeTableRepository
import dagger.Binds
import javax.inject.Singleton

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
}