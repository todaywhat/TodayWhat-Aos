package khs.onmi.meal.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import khs.onmi.meal.data.repository.MealRepositoryImpl
import khs.onmi.meal.domain.repository.MealRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindMealRepository(
        impl: MealRepositoryImpl,
    ): MealRepository
}