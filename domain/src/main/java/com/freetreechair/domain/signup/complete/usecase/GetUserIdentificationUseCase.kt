package com.freetreechair.domain.signup.complete.usecase

import com.freetreechair.domain.signup.complete.repository.CompleteRepository

class GetUserIdentificationUseCase(
    private val repository: CompleteRepository
) {
    operator fun invoke(): HashMap<String, String> {
        return repository.getUserIdentification()
    }
}
