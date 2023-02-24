package com.freetreechair.domain.signup.complete.model

data class DomainSignUpRequest(
    val nickname: String,
    val spicy: Int,
    val sweet: Int,
    val salty: Int,
    val disgusts: List<Int>,
    val evaluates: String
)
