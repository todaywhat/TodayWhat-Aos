package khs.onmi.school.domain.model.response

data class SearchSchoolByNameResponseModel(
    val educationCode: String,
    val schoolCode: String,
    val schoolName: String,
    val schoolLocation: String,
)
