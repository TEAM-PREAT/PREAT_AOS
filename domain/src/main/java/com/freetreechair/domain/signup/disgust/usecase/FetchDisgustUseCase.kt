package com.freetreechair.domain.signup.disgust.usecase

import com.freetreechair.domain.signup.disgust.model.UIDisgust
import com.freetreechair.domain.signup.disgust.repository.DisgustRepository

class FetchDisgustUseCase(
    private val repository: DisgustRepository
) {
    suspend operator fun invoke(query: String?): Result<List<UIDisgust>> {
        return repository.fetchDisgusts(query)
    }
}
