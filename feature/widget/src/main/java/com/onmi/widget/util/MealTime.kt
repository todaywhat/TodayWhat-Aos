package com.onmi.widget.util

sealed class MealTime {

    data object Morning : MealTime()

    data object Lunch : MealTime()

    data object Dinner : MealTime()
}