package khs.onmi.meal.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import khs.onmi.meal.data.datasource.MealDataSource
import khs.onmi.meal.data.datasource.MealDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Singleton
    @Binds
    fun bindMealDataSource(
        impl: MealDataSourceImpl,
    ): MealDataSource
}