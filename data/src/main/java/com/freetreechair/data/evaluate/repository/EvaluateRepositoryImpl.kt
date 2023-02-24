package com.freetreechair.data.evaluate.repository

import com.freetreechair.common.exception.RetrofitFailureStateException
import com.freetreechair.data.NetworkState
import com.freetreechair.data.evaluate.remote.RemoteEvaluateDataSource
import com.freetreechair.data.evaluate.remote.model.mapper.ApiRestaurantMapper
import com.freetreechair.data.preferences.PreferencesDataSource
import com.freetreechair.domain.evaluate.model.UIRestaurant
import com.freetreechair.domain.evaluate.repository.EvaluateRepository
import timber.log.Timber
import javax.inject.Inject

class EvaluateRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    private val remoteEvaluateDataSource: RemoteEvaluateDataSource,
    private val apiRestaurantMapper: ApiRestaurantMapper
) : EvaluateRepository {
    override suspend fun fetchRestaurants(query: String?): Result<List<UIRestaurant>> {
        when (val restaurants = remoteEvaluateDataSource.getRestaurants(query)) {
            is NetworkState.Success -> return Result.success(
                restaurants.body.data?.map {
                    apiRestaurantMapper.mapToDomain(it)
                } ?: emptyList()
            )
            is NetworkState.Failure -> {
                return Result.failure(
                    RetrofitFailureStateException(restaurants.error, restaurants.code)
                )
            }
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_fetchDisgusts")
                .d(restaurants.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_fetchDisgusts")
                .d(restaurants.t)
        }
        return Result.failure(IllegalStateException("서버 에러가 발생하였습니다."))
    }

    override fun saveEvaluates(evaluates: String) {
        preferencesDataSource.saveEvaluates(evaluates)
    }
}
