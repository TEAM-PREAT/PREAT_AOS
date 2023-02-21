package com.freetreechair.sign_up.nickname

enum class NicknameState {
    OVER_TEXT_LIMIT,
    NO_SPECIAL_CHARACTER,
    DUPLICATE_NICKNAME,
    ALLOWED_NICKNAME,
    HAS_NO_STATE
}
