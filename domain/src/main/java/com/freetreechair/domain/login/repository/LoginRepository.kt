package com.freetreechair.domain.login.repository

import com.freetreechair.domain.login.model.DomainLoginRequest
import com.freetreechair.domain.login.model.DomainLoginResponse

interface LoginRepository {
    fun getAccessToken(): String
    fun saveAccessToken(accessToken: String)
    suspend fun makeLoginRequest(loginRequest: DomainLoginRequest): Result<DomainLoginResponse>
}
