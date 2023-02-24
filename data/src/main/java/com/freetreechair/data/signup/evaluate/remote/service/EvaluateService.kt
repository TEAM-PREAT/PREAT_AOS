package com.freetreechair.data.signup.evaluate.remote.service

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.evaluate.remote.model.ApiRestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EvaluateService {
    @GET("v1/signup/search/restaurants")
    suspend fun getRestaurants(
        @Query(value = "query") query: String? = null
    ): NetworkState<BaseResponse<List<ApiRestaurantResponse?>>>
}
