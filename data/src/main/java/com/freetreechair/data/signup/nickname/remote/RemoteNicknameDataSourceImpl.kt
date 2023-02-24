package com.freetreechair.data.signup.nickname.remote

import com.freetreechair.data.util.BaseResponse
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.nickname.remote.model.ApiNicknameResponse
import com.freetreechair.data.signup.nickname.remote.service.NicknameService
import javax.inject.Inject

class RemoteNicknameDataSourceImpl @Inject constructor(
    private val nicknameService: NicknameService
) : RemoteNicknameDataSource {
    override suspend fun checkIsNicknameDuplicated(nickname: String): NetworkState<BaseResponse<ApiNicknameResponse?>> {
        return nicknameService.getNicknameCheck(nickname)
    }
}
