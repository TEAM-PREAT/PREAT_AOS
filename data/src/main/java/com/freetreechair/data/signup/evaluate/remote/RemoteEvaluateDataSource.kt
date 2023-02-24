package com.freetreechair.data.signup.evaluate.remote

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.evaluate.remote.model.ApiRestaurantResponse

interface RemoteEvaluateDataSource {
    suspend fun getRestaurants(query: String? = null): NetworkState<BaseResponse<List<ApiRestaurantResponse?>>>
}
