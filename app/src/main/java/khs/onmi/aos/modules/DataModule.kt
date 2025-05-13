package khs.onmi.aos.modules

import com.onmi.data.datasourceimpl.MealDataSourceImpl
import com.onmi.data.datasourceimpl.SchoolDataSourceImpl
import com.onmi.data.datasourceimpl.TimeTableDataSourceImpl
import com.onmi.data.datasource.MealDataSource
import com.onmi.data.datasource.SchoolDataSource
import com.onmi.data.datasource.TimeTableDataSource
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
        impl: MealDataSourceImpl,
    ): MealDataSource

    @Singleton
    @Binds
    fun bindSchoolDataSource(
        impl: SchoolDataSourceImpl,
    ): SchoolDataSource

    @Singleton
    @Binds
    fun bindTimeTableDataSource(
        impl: TimeTableDataSourceImpl,
    ): TimeTableDataSource
}