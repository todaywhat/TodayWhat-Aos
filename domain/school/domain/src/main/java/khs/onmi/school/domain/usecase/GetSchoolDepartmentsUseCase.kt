package khs.onmi.school.domain.usecase

import khs.onmi.school.domain.repository.SchoolRepository
import javax.inject.Inject

class GetSchoolDepartmentsUseCase @Inject constructor(
    private val repository: SchoolRepository,
) {
    suspend operator fun invoke(
        educationCode: String,
        schoolCode: String,
    ) =
        kotlin.runCatching {
            repository.getSchoolDepartmentsInfo(
                educationCode = educationCode,
                schoolCode = schoolCode,
            )
        }
}