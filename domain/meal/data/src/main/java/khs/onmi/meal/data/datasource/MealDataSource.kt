package khs.onmi.meal.data.datasource

import khs.onmi.meal.data.dto.response.GetTodayMealsResponse

interface MealDataSource {
    suspend fun getTodayMeals(
        educationCode: String,
        schoolCode: String,
        date: String,
    ): GetTodayMealsResponse
}