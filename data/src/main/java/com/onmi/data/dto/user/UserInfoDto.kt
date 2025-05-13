package com.onmi.data.dto.user

import com.onmi.domain.model.user.UserInfoModel

data class UserInfoDto(
    var schoolCode: String,
    var educationCode: String,
    var schoolName: String,
    var schoolType: String,
    var grade: Int,
    var classroom: Int,
    var department: String,
)

fun UserInfoDto.toDomain() = UserInfoModel(
    schoolCode = schoolCode,
    educationCode = educationCode,
    schoolName = schoolName,
    schoolType = schoolType,
    grade = grade,
    classroom = classroom,
    department = department
)

fun UserInfoModel.toDto() = UserInfoDto(
    schoolCode = schoolCode,
    educationCode = educationCode,
    schoolName = schoolName,
    schoolType = schoolType,
    grade = grade,
    classroom = classroom,
    department = department
)