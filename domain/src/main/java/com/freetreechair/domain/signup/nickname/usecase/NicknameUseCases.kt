package com.freetreechair.domain.signup.nickname.usecase

data class NicknameUseCases(
    val checkIsNicknameDuplicatedUseCase: CheckIsNicknameDuplicatedUseCase,
    val saveNicknameUseCase: SaveNicknameUseCase
)
