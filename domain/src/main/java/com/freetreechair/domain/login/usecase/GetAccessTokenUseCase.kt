package com.freetreechair.domain.login.usecase

import com.freetreechair.domain.login.repository.LoginRepository

class GetAccessTokenUseCase(
    private val repository: LoginRepository
) {
    operator fun invoke(): String {
        return repository.getAccessToken()
    }
}
