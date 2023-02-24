package com.freetreechair.data.signup.nickname.remote.service

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.nickname.remote.model.ApiNicknameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NicknameService {
    @GET("v1/auth/nickname/check")
    suspend fun getNicknameCheck(
        @Query(value = "nickname") nickname: String
    ): NetworkState<BaseResponse<ApiNicknameResponse?>>
}
