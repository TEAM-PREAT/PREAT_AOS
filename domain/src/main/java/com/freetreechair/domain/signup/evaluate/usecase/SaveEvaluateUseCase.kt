package com.freetreechair.domain.signup.evaluate.usecase

import com.freetreechair.domain.signup.evaluate.repository.EvaluateRepository

class SaveEvaluateUseCase(
    private val repository: EvaluateRepository
) {
    operator fun invoke(evaluates: String) {
        repository.saveEvaluates(evaluates)
    }
}
