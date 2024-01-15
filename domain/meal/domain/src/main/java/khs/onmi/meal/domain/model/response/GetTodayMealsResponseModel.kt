package khs.onmi.meal.domain.model.response

data class GetTodayMealsResponseModel(
    val breakfast: Pair<List<String>, String>,
    val lunch: Pair<List<String>, String>,
    val dinner: Pair<List<String>, String>,
)
