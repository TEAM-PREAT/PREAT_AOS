package com.freetreechair.data.signup.nickname.remote

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.nickname.remote.model.ApiNicknameResponse

interface RemoteNicknameDataSource {
    suspend fun checkIsNicknameDuplicated(nickname: String): NetworkState<BaseResponse<ApiNicknameResponse?>>
}
