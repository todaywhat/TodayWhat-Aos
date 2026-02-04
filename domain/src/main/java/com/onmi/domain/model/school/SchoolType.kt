package com.onmi.domain.model.school

enum class SchoolType(val key: String) {
    Elementary("els"),
    Middle("mis"),
    High("his"),
    Special("sps");

    companion object {
        fun convertSchoolTypeToKey(type: String): SchoolType {
            return when (type) {
                "초등학교" -> Elementary
                "중학교" -> Middle
                "고등학교" -> High
                "특수학교" -> Special
                else -> throw IllegalArgumentException("알 수 없는 학교 타입입니다: $type")
            }
        }
    }
}