package com.freetreechair.data.nickname.remote

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.nickname.remote.model.ApiNicknameResponse

interface RemoteNicknameDataSource {
    suspend fun checkIsNicknameDuplicated(nickname: String): NetworkState<BaseResponse<ApiNicknameResponse?>>
}
