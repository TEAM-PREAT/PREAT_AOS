package com.freetreechair.domain.auth.login.usecase

data class LoginUseCases(
    val getAccessTokenUseCase: GetAccessTokenUseCase,
    val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    val makeLoginRequestUseCase: MakeLoginRequestUseCase
)
