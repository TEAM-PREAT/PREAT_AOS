package com.freetreechair.data.signup.complete.remote.service

import com.freetreechair.data.signup.complete.remote.model.ApiSignUpRequest
import com.freetreechair.data.signup.complete.remote.model.ApiSignUpResponse
import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import retrofit2.http.Body
import retrofit2.http.POST

interface CompleteService {
    @POST("v1/auth/signup")
    suspend fun postSignUp(
        @Body body: ApiSignUpRequest
    ): NetworkState<BaseResponse<ApiSignUpResponse?>>
}
