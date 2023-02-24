package com.freetreechair.domain.signup.complete.repository

import com.freetreechair.domain.signup.complete.model.DomainSignUpRequest
import com.freetreechair.domain.signup.complete.model.DomainSignUpResponse

interface CompleteRepository {
    fun getUserIdentification() : HashMap<String, String>
    suspend fun makeSignUpRequest(signUpRequest: DomainSignUpRequest): Result<DomainSignUpResponse>
}
