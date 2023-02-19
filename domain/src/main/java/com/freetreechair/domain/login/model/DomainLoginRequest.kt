package com.freetreechair.domain.login.model

data class DomainLoginRequest(
    val platform: String,
    val socialToken: String
)
