package com.freetreechair.data.signup.evaluate.remote.model.mapper

import com.freetreechair.data.util.ApiMapper
import com.freetreechair.data.signup.evaluate.remote.model.ApiRestaurantResponse
import com.freetreechair.domain.signup.evaluate.model.UIRestaurant
import javax.inject.Inject

class ApiRestaurantMapper @Inject constructor() : ApiMapper<ApiRestaurantResponse?, UIRestaurant> {

    override fun mapToDomain(apiEntity: ApiRestaurantResponse?): UIRestaurant {
        return UIRestaurant(
            id = apiEntity?.id ?: -1,
            name = apiEntity?.name.orEmpty(),
            category = apiEntity?.category.orEmpty(),
            address = apiEntity?.address.orEmpty(),
            imageUrl = apiEntity?.imageUrl.orEmpty(),
            rating = apiEntity?.rating?.value?.toFloat() ?: 0.0F
        )
    }
}
