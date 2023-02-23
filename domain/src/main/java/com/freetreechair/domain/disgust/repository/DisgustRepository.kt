package com.freetreechair.domain.disgust.repository

import com.freetreechair.domain.disgust.model.UIDisgust

interface DisgustRepository {
    suspend fun fetchDisgusts(query: String?): Result<List<UIDisgust>>
    fun selectDisgusts(disgustId: Int)
    fun saveDisgusts(disgusts: List<Int>)
}