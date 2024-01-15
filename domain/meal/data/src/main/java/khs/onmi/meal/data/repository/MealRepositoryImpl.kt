package khs.onmi.meal.data.repository

import khs.onmi.meal.data.datasource.MealDataSource
import khs.onmi.meal.data.dto.response.toModel
import khs.onmi.meal.domain.model.response.GetTodayMealsResponseModel
import khs.onmi.meal.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val dataSource: MealDataSource
): MealRepository {
    override suspend fun getTodayMeals(
        educationCode: String,
        schoolCode: String,
        date: String,
    ): GetTodayMealsResponseModel {
        return dataSource.getTodayMeals(
            educationCode = educationCode,
            schoolCode = schoolCode,
            date = date
        ).toModel()
    }
}