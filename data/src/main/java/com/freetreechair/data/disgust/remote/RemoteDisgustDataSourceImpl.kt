package com.freetreechair.data.disgust.remote

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.disgust.remote.model.ApiDisgustResponse
import com.freetreechair.data.disgust.remote.service.DisgustService
import javax.inject.Inject

class RemoteDisgustDataSourceImpl @Inject constructor(
    private val disgustService: DisgustService
) : RemoteDisgustDataSource {
    override suspend fun getDisgusts(query: String?): NetworkState<BaseResponse<List<ApiDisgustResponse?>>> {
        return disgustService.getDisgusts(query)
    }
}
