package com.freetreechair.domain.signup.nickname.usecase

import com.freetreechair.domain.signup.nickname.repository.NicknameRepository

class CheckIsNicknameDuplicatedUseCase(
    private val repository: NicknameRepository
) {
    suspend operator fun invoke(nickname: String): Result<Boolean> {
        return repository.checkIsNicknameDuplicated(nickname)
    }
}
