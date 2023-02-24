package com.freetreechair.data.signup.disgust.remote

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.disgust.remote.model.ApiDisgustResponse
import com.freetreechair.data.signup.disgust.remote.service.DisgustService
import javax.inject.Inject

class RemoteDisgustDataSourceImpl @Inject constructor(
    private val disgustService: DisgustService
) : RemoteDisgustDataSource {
    override suspend fun getDisgusts(query: String?): NetworkState<BaseResponse<List<ApiDisgustResponse?>>> {
        return disgustService.getDisgusts(query)
    }
}
