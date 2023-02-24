package com.freetreechair.data.signup.complete.repository

import com.freetreechair.common.exception.RetrofitFailureStateException
import com.freetreechair.data.signup.complete.remote.RemoteCompleteDataSource
import com.freetreechair.data.signup.complete.remote.model.ApiRestaurantRating
import com.freetreechair.data.signup.complete.remote.model.ApiSignUpRequest
import com.freetreechair.data.util.NetworkState
import com.freetreechair.data.util.preferences.PreferencesDataSource
import com.freetreechair.domain.signup.complete.model.DomainSignUpRequest
import com.freetreechair.domain.signup.complete.model.DomainSignUpResponse
import com.freetreechair.domain.signup.complete.repository.CompleteRepository
import timber.log.Timber
import javax.inject.Inject

class CompleteRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    private val remoteCompleteDataSource: RemoteCompleteDataSource
) : CompleteRepository {
    override fun getUserIdentification(): HashMap<String, String> {
        return preferencesDataSource.getUserIdentification()
    }

    override suspend fun makeSignUpRequest(signUpRequest: DomainSignUpRequest): Result<DomainSignUpResponse> {
        when (
            val response = remoteCompleteDataSource.makeSignUpRequest(
                ApiSignUpRequest(
                    nickname = signUpRequest.nickname,
                    sweet = signUpRequest.sweet,
                    spicy = signUpRequest.spicy,
                    salty = signUpRequest.salty,
                    hateFoods = signUpRequest.disgusts,
                    reviews = mapToApi(signUpRequest.evaluates)
                )
            )
        ) {
            is NetworkState.Success -> return Result.success(
                DomainSignUpResponse(
                    userId = response.body.data?.userId ?: -1,
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
                "${this.javaClass.name}_makeSignUpRequest"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_makeSignUpRequest"
            )
        }
        return Result.failure(IllegalStateException("서버 에러가 발생하였습니다."))
    }

    private fun mapToApi(evaluates: String): List<ApiRestaurantRating> {
        val arr: ArrayList<ApiRestaurantRating> = arrayListOf()
        val evaluateDetail = evaluates.split(",")
        evaluateDetail.forEach {
            arr.add(
                ApiRestaurantRating(
                    restaurantId = it.split("-").first(),
                    rating = it.split("-").last().toDouble()
                )
            )
        }
        return arr.toList()
    }
}
