package com.freetreechair.data.auth.login.remote.service

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.auth.login.remote.model.ApiLoginRequest
import com.freetreechair.data.auth.login.remote.model.ApiLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("v1/auth/login")
    suspend fun postLogin(
        @Body body: ApiLoginRequest
    ): NetworkState<BaseResponse<ApiLoginResponse?>>
}
