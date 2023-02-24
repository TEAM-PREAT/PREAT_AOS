package com.freetreechair.data.signup.complete.remote

import com.freetreechair.data.signup.complete.remote.model.ApiSignUpRequest
import com.freetreechair.data.signup.complete.remote.model.ApiSignUpResponse
import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState

interface RemoteCompleteDataSource {
    suspend fun makeSignUpRequest(signUpRequest: ApiSignUpRequest): NetworkState<BaseResponse<ApiSignUpResponse?>>
}
