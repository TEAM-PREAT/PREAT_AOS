package com.freetreechair.domain.nickname.usecase

import com.freetreechair.domain.nickname.repository.NicknameRepository

class CheckIsNicknameDuplicatedUseCase(
    private val repository: NicknameRepository
) {
    suspend operator fun invoke(nickname: String): Result<Boolean> {
        return repository.checkIsNicknameDuplicated(nickname)
    }
}
