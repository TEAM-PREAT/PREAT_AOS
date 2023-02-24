package com.freetreechair.domain.signup.nickname.repository

interface NicknameRepository {
    suspend fun checkIsNicknameDuplicated(nickname: String): Result<Boolean>
    fun saveNickname(nickname: String)
}
