package khs.onmi.school.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import khs.onmi.school.data.datasource.SchoolDataSource
import khs.onmi.school.data.datasource.SchoolDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Singleton
    @Binds
    fun bindSchoolDataSource(
        impl: SchoolDataSourceImpl,
    ): SchoolDataSource
}