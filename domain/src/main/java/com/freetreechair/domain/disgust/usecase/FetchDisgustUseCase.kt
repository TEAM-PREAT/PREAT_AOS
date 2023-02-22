package com.freetreechair.domain.disgust.usecase

import com.freetreechair.domain.disgust.model.UIDisgust
import com.freetreechair.domain.disgust.repository.DisgustRepository

class FetchDisgustUseCase(
    private val repository: DisgustRepository
) {
    suspend operator fun invoke(query: String?): Result<List<UIDisgust>> {
        return repository.fetchDisgusts(query)
    }
}
