package com.freetreechair.domain.auth.login.model

data class DomainLoginResponse(
    val isNewUser: Boolean,
    val accessToken: String
)
