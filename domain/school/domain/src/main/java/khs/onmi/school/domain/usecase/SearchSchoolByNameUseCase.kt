package khs.onmi.school.domain.usecase

import khs.onmi.school.domain.model.request.SearchSchoolByNameRequestModel
import khs.onmi.school.domain.repository.SchoolRepository
import javax.inject.Inject

class SearchSchoolByNameUseCase @Inject constructor(
    private val repository: SchoolRepository,
) {
    suspend operator fun invoke(searchKeyword: SearchSchoolByNameRequestModel) =
        kotlin.runCatching {
            repository.searchSchoolByName(searchKeyword = searchKeyword)
        }
}