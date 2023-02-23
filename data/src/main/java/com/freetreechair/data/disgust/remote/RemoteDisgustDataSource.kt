package com.freetreechair.data.disgust.remote

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.disgust.remote.model.ApiDisgustResponse

interface RemoteDisgustDataSource {
    suspend fun getDisgusts(query: String? = null): NetworkState<BaseResponse<List<ApiDisgustResponse?>>>
}
