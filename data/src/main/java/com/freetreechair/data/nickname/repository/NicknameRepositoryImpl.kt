package com.freetreechair.data.nickname.repository

import com.freetreechair.common.exception.RetrofitFailureStateException
import com.freetreechair.data.NetworkState
import com.freetreechair.data.nickname.remote.RemoteNicknameDataSource
import com.freetreechair.data.preferences.PreferencesDataSource
import com.freetreechair.domain.nickname.repository.NicknameRepository
import timber.log.Timber
import javax.inject.Inject

class NicknameRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    private val remoteNicknameDataSource: RemoteNicknameDataSource
) : NicknameRepository {
    override suspend fun checkIsNicknameDuplicated(nickname: String): Result<Boolean> {
        when (val response = remoteNicknameDataSource.checkIsNicknameDuplicated(nickname)) {
            is NetworkState.Success -> return Result.success(
                response.body.data?.isAvailable ?: false
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_checkIsNicknameDuplicated")
                .d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_checkIsNicknameDuplicated")
                .d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override fun saveNickname(nickname: String) {
        preferencesDataSource.saveNickname(nickname)
    }
}
