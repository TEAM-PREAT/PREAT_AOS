package com.freetreechair.data.signup.evaluate.remote

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.evaluate.remote.model.ApiRestaurantResponse
import com.freetreechair.data.signup.evaluate.remote.service.EvaluateService
import javax.inject.Inject

class RemoteEvaluateDataSourceImpl @Inject constructor(
    private val evaluateService: EvaluateService
) : RemoteEvaluateDataSource {
    override suspend fun getRestaurants(query: String?): NetworkState<BaseResponse<List<ApiRestaurantResponse?>>> {
        return evaluateService.getRestaurants(query)
    }
}
