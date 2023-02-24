package com.freetreechair.domain.auth.login.usecase

import com.freetreechair.domain.auth.login.repository.LoginRepository

class GetAccessTokenUseCase(
    private val repository: LoginRepository
) {
    operator fun invoke(): String {
        return repository.getAccessToken()
    }
}
