package com.onmi.data.service

import com.onmi.data.dto.meal.response.GetTodayMealsResponse

interface MealService {

    suspend fun getTodayMeals(
        educationCode: String,
        schoolCode: String,
        date: String,
    ): GetTodayMealsResponse
}