package com.freetreechair.domain.signup.complete.usecase

import com.freetreechair.domain.signup.complete.model.DomainSignUpRequest
import com.freetreechair.domain.signup.complete.model.DomainSignUpResponse
import com.freetreechair.domain.signup.complete.repository.CompleteRepository

class MakeSignUpRequestUseCase(
    private val repository: CompleteRepository
) {
    suspend operator fun invoke(signUpRequest: DomainSignUpRequest): Result<DomainSignUpResponse> {
        return repository.makeSignUpRequest(signUpRequest)
    }
}
