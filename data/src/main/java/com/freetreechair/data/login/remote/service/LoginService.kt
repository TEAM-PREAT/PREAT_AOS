package com.freetreechair.data.login.remote.service

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.login.remote.model.ApiLoginRequest
import com.freetreechair.data.login.remote.model.ApiLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("v1/auth/login")
    suspend fun postLogin(
        @Body body: ApiLoginRequest
    ): NetworkState<BaseResponse<ApiLoginResponse?>>
}
