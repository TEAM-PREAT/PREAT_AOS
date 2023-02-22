package com.freetreechair.data.disgust.remote.service

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.disgust.remote.model.ApiDisgustResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DisgustService {
    @GET("v1/dislikes")
    suspend fun getDisgusts(
        @Query("query") query: String? = null
    ): NetworkState<BaseResponse<List<ApiDisgustResponse?>>>
}
