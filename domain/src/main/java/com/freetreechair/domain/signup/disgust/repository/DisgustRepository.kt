package com.freetreechair.domain.signup.disgust.repository

import com.freetreechair.domain.signup.disgust.model.UIDisgust

interface DisgustRepository {
    suspend fun fetchDisgusts(query: String?): Result<List<UIDisgust>>
    fun saveDisgusts(disgusts: String)
}
