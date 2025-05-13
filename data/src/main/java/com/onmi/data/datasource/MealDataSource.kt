package com.onmi.data.datasource

import com.onmi.data.dto.meal.response.GetTodayMealsResponse

interface MealDataSource {

    suspend fun getMeals(
        educationCode: String,
        schoolCode: String,
        date: String,
    ): GetTodayMealsResponse
}