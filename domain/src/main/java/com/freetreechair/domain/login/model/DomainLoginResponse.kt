package com.freetreechair.domain.login.model

data class DomainLoginResponse(
    val isNewUser: Boolean,
    val accessToken: String
)
