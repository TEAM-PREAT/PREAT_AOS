package com.freetreechair.domain.evaluate.repository

import com.freetreechair.domain.evaluate.model.UIRestaurant

interface EvaluateRepository {
    suspend fun fetchRestaurants(query: String?): Result<List<UIRestaurant>>
    fun saveEvaluates(evaluates: String)
}
