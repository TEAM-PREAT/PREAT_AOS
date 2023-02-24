package com.freetreechair.domain.signup.evaluate.repository

import com.freetreechair.domain.signup.evaluate.model.UIRestaurant

interface EvaluateRepository {
    suspend fun fetchRestaurants(query: String?): Result<List<UIRestaurant>>
    fun saveEvaluates(evaluates: String)
}
