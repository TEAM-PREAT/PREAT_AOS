package com.freetreechair.domain.login.usecase

import com.freetreechair.domain.login.model.DomainLoginRequest
import com.freetreechair.domain.login.model.DomainLoginResponse
import com.freetreechair.domain.login.repository.LoginRepository

class MakeLoginRequestUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(loginRequest: DomainLoginRequest): Result<DomainLoginResponse> {
        return repository.makeLoginRequest(loginRequest)
    }
}
