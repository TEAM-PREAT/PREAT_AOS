package com.freetreechair.domain.signup.evaluate.usecase

import com.freetreechair.domain.signup.evaluate.model.UIRestaurant
import com.freetreechair.domain.signup.evaluate.repository.EvaluateRepository

class FetchRestaurantUseCase(
    private val repository: EvaluateRepository
) {
    suspend operator fun invoke(query: String?): Result<List<UIRestaurant>> {
        return repository.fetchRestaurants(query)
    }
}

