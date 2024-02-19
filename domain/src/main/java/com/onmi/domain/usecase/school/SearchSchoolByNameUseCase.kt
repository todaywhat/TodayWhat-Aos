package com.onmi.domain.usecase.school

import com.onmi.domain.repository.SchoolRepository
import javax.inject.Inject

class SearchSchoolByNameUseCase @Inject constructor(
    private val repository: SchoolRepository,
) {
    suspend operator fun invoke(searchKeyword: String) =
        kotlin.runCatching {
            repository.searchSchoolByName(searchKeyword = searchKeyword)
        }
}