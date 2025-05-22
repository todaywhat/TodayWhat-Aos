package com.onmi.data.datasourceimpl

import com.onmi.data.dto.meal.response.GetTodayMealsListResponse
import com.onmi.data.dto.meal.response.GetTodayMealsResponse
import com.onmi.data.dto.meal.response.SchoolMealInfo
import com.onmi.data.datasource.MealDataSource
import com.onmi.data.utils.bodyOrThrow
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

    private fun String.removeDetailInfo(): String {
        val regex = Regex("\\([^)]*\\)")
        return this.replace(regex, "")
    }

    private fun List<SchoolMealInfo>.findMealInfo(type: String): Pair<List<String>, String> {
        return this.find { findData -> findData.type == type }?.let { schoolMealInfo ->
            Pair(
                schoolMealInfo.meal.split("<br/>").map { it.removeDetailInfo() },
                schoolMealInfo.kcal
            )
        } ?: Pair(emptyList(), "")
    }
}