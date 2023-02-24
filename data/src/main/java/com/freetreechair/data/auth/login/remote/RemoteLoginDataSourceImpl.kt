package com.freetreechair.data.auth.login.remote

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.auth.login.remote.model.ApiLoginRequest
import com.freetreechair.data.auth.login.remote.model.ApiLoginResponse
import com.freetreechair.data.auth.login.remote.service.LoginService
import javax.inject.Inject

class RemoteLoginDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : RemoteLoginDataSource {
    override suspend fun makeLoginRequest(loginRequest: ApiLoginRequest): NetworkState<BaseResponse<ApiLoginResponse?>> {
        return loginService.postLogin(loginRequest)
    }
}
