package com.freetreechair.domain.login.usecase

import com.freetreechair.domain.login.repository.LoginRepository

class SaveAccessTokenUseCase(
    private val repository: LoginRepository
) {
    operator fun invoke(accessToken: String) {
        repository.saveAccessToken(accessToken)
    }
}
