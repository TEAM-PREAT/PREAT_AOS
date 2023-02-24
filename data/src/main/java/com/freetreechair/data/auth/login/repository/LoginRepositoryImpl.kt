package com.freetreechair.data.auth.login.repository

import com.freetreechair.common.exception.RetrofitFailureStateException
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.auth.login.remote.RemoteLoginDataSource
import com.freetreechair.data.auth.login.remote.model.ApiLoginRequest
import com.freetreechair.data.util.preferences.PreferencesDataSource
import com.freetreechair.domain.auth.login.model.DomainLoginRequest
import com.freetreechair.domain.auth.login.model.DomainLoginResponse
import com.freetreechair.domain.auth.login.repository.LoginRepository
import timber.log.Timber
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    private val remoteLoginDataSource: RemoteLoginDataSource
) : LoginRepository {
    override fun getAccessToken(): String {
        return preferencesDataSource.getAccessToken()
    }

    override fun saveAccessToken(accessToken: String) {
        preferencesDataSource.saveAccessToken(accessToken)
    }

    override suspend fun makeLoginRequest(loginRequest: DomainLoginRequest): Result<DomainLoginResponse> {
        when (
            val response = remoteLoginDataSource.makeLoginRequest(
                ApiLoginRequest(
                    platform = loginRequest.platform,
                    socialToken = loginRequest.socialToken
                )
            )
        ) {
            is NetworkState.Success -> return Result.success(
                DomainLoginResponse(
                    isNewUser = response.body.data?.isNewUser ?: true,
                    accessToken = response.body.data?.accessToken.orEmpty()
                )
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.d(
                response.error,
                "${this.javaClass.name}_makeLoginRequest"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_makeLoginRequest"
            )
        }
        return Result.failure(IllegalStateException("서버 에러가 발생하였습니다."))
    }
}
