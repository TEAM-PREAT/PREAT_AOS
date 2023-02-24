package com.freetreechair.data.evaluate.remote

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.disgust.remote.model.ApiDisgustResponse
import com.freetreechair.data.evaluate.remote.model.ApiRestaurantResponse
import com.freetreechair.data.evaluate.remote.service.EvaluateService
import javax.inject.Inject

class RemoteEvaluateDataSourceImpl @Inject constructor(
    private val evaluateService: EvaluateService
) : RemoteEvaluateDataSource {
    override suspend fun getRestaurants(query: String?): NetworkState<BaseResponse<List<ApiRestaurantResponse?>>> {
        return evaluateService.getRestaurants(query)
    }
}
