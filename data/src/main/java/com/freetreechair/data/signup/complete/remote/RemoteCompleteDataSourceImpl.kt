package com.freetreechair.data.signup.complete.remote

import com.freetreechair.data.signup.complete.remote.model.ApiSignUpRequest
import com.freetreechair.data.signup.complete.remote.model.ApiSignUpResponse
import com.freetreechair.data.signup.complete.remote.service.CompleteService
import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import javax.inject.Inject

class RemoteCompleteDataSourceImpl @Inject constructor(
    private val completeService: CompleteService
) : RemoteCompleteDataSource {
    override suspend fun makeSignUpRequest(signUpRequest: ApiSignUpRequest): NetworkState<BaseResponse<ApiSignUpResponse?>> {
        return completeService.postSignUp(signUpRequest)
    }
}
