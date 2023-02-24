package com.freetreechair.data.evaluate.remote

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.evaluate.remote.model.ApiRestaurantResponse

interface RemoteEvaluateDataSource {
    suspend fun getRestaurants(query: String? = null): NetworkState<BaseResponse<List<ApiRestaurantResponse?>>>
}
