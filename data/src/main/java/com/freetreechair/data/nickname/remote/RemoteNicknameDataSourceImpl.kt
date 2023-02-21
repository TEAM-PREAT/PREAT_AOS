package com.freetreechair.data.nickname.remote

import com.freetreechair.data.BaseResponse
import com.freetreechair.data.NetworkState
import com.freetreechair.data.nickname.remote.model.ApiNicknameResponse
import com.freetreechair.data.nickname.remote.service.NicknameService
import javax.inject.Inject

class RemoteNicknameDataSourceImpl @Inject constructor(
    private val nicknameService: NicknameService
) : RemoteNicknameDataSource {
    override suspend fun checkIsNicknameDuplicated(nickname: String): NetworkState<BaseResponse<ApiNicknameResponse?>> {
        return nicknameService.getNicknameCheck(nickname)
    }
}
