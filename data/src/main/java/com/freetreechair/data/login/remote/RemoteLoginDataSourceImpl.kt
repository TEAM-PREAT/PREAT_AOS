package com.freetreechair.data.login.remote

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.login.remote.model.ApiLoginRequest
import com.freetreechair.data.login.remote.model.ApiLoginResponse
import com.freetreechair.data.login.remote.service.LoginService
import javax.inject.Inject

class RemoteLoginDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : RemoteLoginDataSource {
    override suspend fun makeLoginRequest(loginRequest: ApiLoginRequest): NetworkState<BaseResponse<ApiLoginResponse?>> {
        return loginService.postLogin(loginRequest)
    }
}
