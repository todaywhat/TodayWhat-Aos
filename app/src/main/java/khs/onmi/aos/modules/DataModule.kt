package khs.onmi.aos.modules

import com.onmi.data.datasource.MealDataSource
import com.onmi.data.datasource.SchoolDataSource
import com.onmi.data.datasource.TimeTableDataSource
import com.onmi.data.service.MealService
import com.onmi.data.service.SchoolService
import com.onmi.data.service.TimeTableService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Singleton
    @Binds
    fun bindMealDataSource(
        impl: MealDataSource,
    ): MealService

    @Singleton
    @Binds
    fun bindSchoolDataSource(
        impl: SchoolDataSource,
    ): SchoolService

    @Singleton
    @Binds
    fun bindTimeTableDataSource(
        impl: TimeTableDataSource,
    ): TimeTableService
}