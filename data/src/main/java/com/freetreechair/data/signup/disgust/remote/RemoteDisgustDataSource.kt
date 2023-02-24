package com.freetreechair.data.signup.disgust.remote

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.disgust.remote.model.ApiDisgustResponse

interface RemoteDisgustDataSource {
    suspend fun getDisgusts(query: String? = null): NetworkState<BaseResponse<List<ApiDisgustResponse?>>>
}
