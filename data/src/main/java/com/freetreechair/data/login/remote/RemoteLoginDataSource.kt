package com.freetreechair.data.login.remote

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.login.remote.model.ApiLoginRequest
import com.freetreechair.data.login.remote.model.ApiLoginResponse

interface RemoteLoginDataSource {
    suspend fun makeLoginRequest(loginRequest: ApiLoginRequest): NetworkState<BaseResponse<ApiLoginResponse?>>
}
