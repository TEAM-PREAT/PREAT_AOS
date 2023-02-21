package com.freetreechair.data.nickname.remote.service

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.nickname.remote.model.ApiNicknameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NicknameService {
    @GET("v1/auth/nickname/check")
    suspend fun getNicknameCheck(
        @Query("nickname") nickname: String
    ): NetworkState<BaseResponse<ApiNicknameResponse?>>
}
