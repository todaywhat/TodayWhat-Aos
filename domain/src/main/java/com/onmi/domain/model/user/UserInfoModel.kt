package com.onmi.domain.model.user

data class UserInfoModel(
    var schoolCode: String,
    var educationCode: String,
    var schoolName: String,
    var schoolType: String,
    var grade: Int,
    var classroom: Int,
    var department: String,
)
