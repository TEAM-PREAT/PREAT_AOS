package com.freetreechair.domain.login.usecase

data class LoginUseCases(
    val getAccessTokenUseCase: GetAccessTokenUseCase,
    val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    val makeLoginRequestUseCase: MakeLoginRequestUseCase
)
