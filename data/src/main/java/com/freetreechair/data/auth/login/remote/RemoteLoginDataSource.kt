package com.freetreechair.data.auth.login.remote

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.auth.login.remote.model.ApiLoginRequest
import com.freetreechair.data.auth.login.remote.model.ApiLoginResponse

interface RemoteLoginDataSource {
    suspend fun makeLoginRequest(loginRequest: ApiLoginRequest): NetworkState<BaseResponse<ApiLoginResponse?>>
}
