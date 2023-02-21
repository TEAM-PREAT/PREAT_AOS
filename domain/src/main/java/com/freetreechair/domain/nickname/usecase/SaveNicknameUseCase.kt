package com.freetreechair.domain.nickname.usecase

import com.freetreechair.domain.nickname.repository.NicknameRepository

class SaveNicknameUseCase(
    private val repository: NicknameRepository
) {
    operator fun invoke(nickname: String) {
        repository.saveNickname(nickname)
    }
}
