package com.freetreechair.data.signup.disgust.remote.service

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.disgust.remote.model.ApiDisgustResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DisgustService {
    @GET("v1/dislikes")
    suspend fun getDisgusts(
        @Query(value = "query") query: String? = null
    ): NetworkState<BaseResponse<List<ApiDisgustResponse?>>>
}
