package com.freetreechair.domain.auth.login.model

data class DomainLoginRequest(
    val platform: String,
    val socialToken: String
)
