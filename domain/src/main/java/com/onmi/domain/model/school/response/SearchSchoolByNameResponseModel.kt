package com.onmi.domain.model.school.response

data class SearchSchoolByNameResponseModel(
    val educationCode: String,
    val schoolCode: String,
    val schoolName: String,
    val schoolLocation: String,
    val schoolType: String,
)
