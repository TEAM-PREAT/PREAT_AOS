package com.freetreechair.domain.signup.taste.usecase

import com.freetreechair.domain.signup.taste.repository.TasteRepository

class SaveTasteUseCase(
    private val repository: TasteRepository
) {
    operator fun invoke(tastes: String) {
        repository.saveTastes(tastes)
    }
}
