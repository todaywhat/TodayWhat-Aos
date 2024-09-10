package com.onmi.data.service

interface TimeTableService {

    suspend fun getTimeTable(
        schoolCode: String,
        schoolType: String,
        educationCode: String,
        grade: Int,
        `class`: Int,
        department: String,
        date: String,
    ): List<String>
}