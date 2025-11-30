package com.onmi.domain.repository

import com.onmi.domain.model.school.SchoolType

interface TimeTableRepository {

    suspend fun getTimeTable(
        schoolCode: String,
        schoolType: SchoolType,
        educationCode: String,
        grade: Int,
        `class`: Int,
        department: String?,
        date: String,
    ): List<String>?
}