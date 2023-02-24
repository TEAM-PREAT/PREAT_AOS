package com.freetreechair.domain.auth.login.usecase

import com.freetreechair.domain.auth.login.repository.LoginRepository

class SaveAccessTokenUseCase(
    private val repository: LoginRepository
) {
    operator fun invoke(accessToken: String) {
        repository.saveAccessToken(accessToken)
    }
}
