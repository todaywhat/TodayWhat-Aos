package khs.onmi.meal.domain.repository

import khs.onmi.meal.domain.model.response.GetTodayMealsResponseModel

interface MealRepository {

    suspend fun getTodayMeals(
        educationCode: String,
        schoolCode: String,
        date: String,
    ): GetTodayMealsResponseModel
}