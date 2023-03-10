package com.freetreechair.data.signup.nickname.repository

import com.freetreechair.common.exception.RetrofitFailureStateException
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.signup.nickname.remote.RemoteNicknameDataSource
import com.freetreechair.data.util.preferences.PreferencesDataSource
import com.freetreechair.domain.signup.nickname.repository.NicknameRepository
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
        return Result.failure(IllegalStateException("서버 에러가 발생하였습니다."))
    }

    override fun saveNickname(nickname: String) {
        preferencesDataSource.saveNickname(nickname)
    }
}
