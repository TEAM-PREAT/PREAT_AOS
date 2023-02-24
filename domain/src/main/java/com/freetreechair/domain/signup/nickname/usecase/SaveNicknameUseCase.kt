package com.freetreechair.domain.signup.nickname.usecase

import com.freetreechair.domain.signup.nickname.repository.NicknameRepository

class SaveNicknameUseCase(
    private val repository: NicknameRepository
) {
    operator fun invoke(nickname: String) {
        repository.saveNickname(nickname)
    }
}
