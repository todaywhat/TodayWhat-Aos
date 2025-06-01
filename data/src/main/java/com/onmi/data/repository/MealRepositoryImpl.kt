package com.onmi.data.repository

import com.onmi.data.dto.meal.response.toModel
import com.onmi.data.datasource.MealDataSource
import com.onmi.domain.model.meal.response.GetMealsResponseModel
import com.onmi.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val service: MealDataSource,
) : MealRepository {

    override suspend fun getMeals(
        educationCode: String,
        schoolCode: String,
        date: String,
    ): GetMealsResponseModel {
        return service.getMeals(
            educationCode = educationCode,
            schoolCode = schoolCode,
            date = date
        ).toModel()
    }
}