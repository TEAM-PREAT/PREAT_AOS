package com.freetreechair.domain.evaluate.usecase

import com.freetreechair.domain.evaluate.repository.EvaluateRepository

class SaveEvaluateUseCase(
    private val repository: EvaluateRepository
) {
    operator fun invoke(evaluates: String) {
        repository.saveEvaluates(evaluates)
    }
}
