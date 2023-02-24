package com.freetreechair.domain.auth.login.usecase

import com.freetreechair.domain.auth.login.model.DomainLoginRequest
import com.freetreechair.domain.auth.login.model.DomainLoginResponse
import com.freetreechair.domain.auth.login.repository.LoginRepository

class MakeLoginRequestUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(loginRequest: DomainLoginRequest): Result<DomainLoginResponse> {
        return repository.makeLoginRequest(loginRequest)
    }
}
