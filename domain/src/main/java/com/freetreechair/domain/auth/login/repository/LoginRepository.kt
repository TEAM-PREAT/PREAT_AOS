package com.freetreechair.domain.auth.login.repository

import com.freetreechair.domain.auth.login.model.DomainLoginRequest
import com.freetreechair.domain.auth.login.model.DomainLoginResponse

interface LoginRepository {
    fun getAccessToken(): String
    fun saveAccessToken(accessToken: String)
    suspend fun makeLoginRequest(loginRequest: DomainLoginRequest): Result<DomainLoginResponse>
}
