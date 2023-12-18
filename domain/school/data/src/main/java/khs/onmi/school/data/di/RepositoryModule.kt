package khs.onmi.school.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import khs.onmi.school.data.repository.SchoolRepositoryImpl
import khs.onmi.school.domain.repository.SchoolRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindSchoolRepository(
        impl: SchoolRepositoryImpl,
    ): SchoolRepository
}