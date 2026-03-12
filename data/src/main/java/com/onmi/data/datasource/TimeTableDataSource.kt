package com.onmi.data.datasource

import com.onmi.domain.model.school.SchoolType

interface TimeTableDataSource {

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