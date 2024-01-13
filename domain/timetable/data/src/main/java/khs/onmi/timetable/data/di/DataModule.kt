package khs.onmi.timetable.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import khs.onmi.timetable.data.datasource.TimeTableDataSource
import khs.onmi.timetable.data.datasource.TimeTableDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Singleton
    @Binds
    fun bindTimeTableDataSource(
        impl: TimeTableDataSourceImpl,
    ): TimeTableDataSource
}