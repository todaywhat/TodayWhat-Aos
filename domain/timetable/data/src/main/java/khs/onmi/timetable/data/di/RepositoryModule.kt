package khs.onmi.timetable.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import khs.onmi.timetable.data.repository.TimeTableRepositoryImpl
import khs.onmi.timetable.domain.repository.TimeTableRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindTimeTableRepository(
        impl: TimeTableRepositoryImpl,
    ): TimeTableRepository
}