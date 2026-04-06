package com.onmi.data.datasourceimpl

import com.onmi.data.dto.meal.response.GetTodayMealsListResponse
import com.onmi.data.dto.meal.response.GetTodayMealsResponse
import com.onmi.data.dto.meal.response.SchoolMealInfo
import com.onmi.data.datasource.MealDataSource
import com.onmi.data.utils.bodyOrThrow
import com.onmi.domain.model.meal.MealMenuItem
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class MealDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
): MealDataSource {

    override suspend fun getMeals(
        educationCode: String,
        schoolCode: String,
        date: String,
    ): GetTodayMealsResponse {
        val response = httpClient.get {
            url("/hub/mealServiceDietInfo")
            parameter("ATPT_OFCDC_SC_CODE", educationCode)
            parameter("SD_SCHUL_CODE", schoolCode)
            parameter("MLSV_YMD", date)
        }.bodyOrThrow<GetTodayMealsListResponse>().meals!![1].row

        return GetTodayMealsResponse(
            breakfast = response.findMealInfo("조식"),
            lunch = response.findMealInfo("중식"),
            dinner = response.findMealInfo("석식")
        )
    }

    private fun String.parseMealMenuItem(): MealMenuItem {
        val regex = Regex("\\(([^)]*?)\\)\\s*$")
        val match = regex.find(this.trim())
        return if (match != null) {
            val name = this.substring(0, match.range.first).trim()
            val allergyIds = match.groupValues[1]
                .split(".")
                .mapNotNull { it.trim().toIntOrNull() }
            MealMenuItem(name = name, allergyIds = allergyIds)
        } else {
            MealMenuItem(name = this.trim(), allergyIds = emptyList())
        }
    }

    private fun List<SchoolMealInfo>.findMealInfo(type: String): Pair<List<MealMenuItem>, String> {
        return this.find { findData -> findData.type == type }?.let { schoolMealInfo ->
            Pair(
                schoolMealInfo.meal.split("<br/>").map { it.parseMealMenuItem() },
                schoolMealInfo.kcal
            )
        } ?: Pair(emptyList(), "")
    }
}