package com.freetreechair.domain.taste.usecase

import com.freetreechair.domain.taste.repository.TasteRepository

class SaveTasteUseCase(
    private val repository: TasteRepository
) {
    operator fun invoke(tastes: String) {
        repository.saveTastes(tastes)
    }
}
