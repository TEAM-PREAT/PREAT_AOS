package com.freetreechair.domain.evaluate.usecase

import com.freetreechair.domain.evaluate.model.UIRestaurant
import com.freetreechair.domain.evaluate.repository.EvaluateRepository

class FetchRestaurantUseCase(
    private val repository: EvaluateRepository
) {
    suspend operator fun invoke(query: String?): Result<List<UIRestaurant>> {
        return repository.fetchRestaurants(query)
    }
}

