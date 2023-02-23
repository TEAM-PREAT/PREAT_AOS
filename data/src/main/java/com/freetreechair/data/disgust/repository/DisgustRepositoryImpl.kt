package com.freetreechair.data.disgust.repository

import com.freetreechair.common.exception.RetrofitFailureStateException
import com.freetreechair.data.NetworkState
import com.freetreechair.data.disgust.remote.RemoteDisgustDataSource
import com.freetreechair.data.disgust.remote.model.mapper.ApiDisgustMapper
import com.freetreechair.data.preferences.PreferencesDataSource
import com.freetreechair.domain.disgust.model.UIDisgust
import com.freetreechair.domain.disgust.repository.DisgustRepository
import timber.log.Timber
import javax.inject.Inject

class DisgustRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    private val remoteDisgustDataSource: RemoteDisgustDataSource,
    private val apiDisgustMapper: ApiDisgustMapper
) : DisgustRepository {
    override suspend fun fetchDisgusts(query: String?): Result<List<UIDisgust>> {
        when (val disgusts = remoteDisgustDataSource.getDisgusts(query)) {
            is NetworkState.Success -> return Result.success(
                disgusts.body.data?.map {
                    apiDisgustMapper.mapToDomain(it)
                } ?: emptyList()
            )
            is NetworkState.Failure -> {
                return Result.failure(
                    RetrofitFailureStateException(disgusts.error, disgusts.code)
                )
            }
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_fetchDisgusts")
                .d(disgusts.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_fetchDisgusts")
                .d(disgusts.t)
        }
        return Result.failure(IllegalStateException("서버 에러가 발생하였습니다."))
    }

    override fun saveDisgusts(disgusts: String) {
        preferencesDataSource.saveDisgusts(disgusts)
    }
}
