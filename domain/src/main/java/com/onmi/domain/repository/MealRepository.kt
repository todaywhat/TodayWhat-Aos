package com.onmi.domain.repository

import com.onmi.domain.model.meal.response.GetMealsResponseModel

interface MealRepository {

    suspend fun getMeals(
        educationCode: String,
        schoolCode: String,
        date: String,
    ): GetMealsResponseModel
}