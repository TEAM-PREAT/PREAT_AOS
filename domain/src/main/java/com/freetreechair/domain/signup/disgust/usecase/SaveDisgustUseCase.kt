package com.freetreechair.domain.signup.disgust.usecase

import com.freetreechair.domain.signup.disgust.repository.DisgustRepository

class SaveDisgustUseCase(
    private val repository: DisgustRepository
) {
    operator fun invoke(disgusts: String) {
        repository.saveDisgusts(disgusts)
    }
}
