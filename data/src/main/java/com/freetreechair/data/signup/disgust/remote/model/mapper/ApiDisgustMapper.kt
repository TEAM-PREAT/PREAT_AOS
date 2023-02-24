package com.freetreechair.data.signup.disgust.remote.model.mapper

import com.freetreechair.data.util.ApiMapper
import com.freetreechair.data.signup.disgust.remote.model.ApiDisgustResponse
import com.freetreechair.domain.signup.disgust.model.UIDisgust
import javax.inject.Inject

class ApiDisgustMapper @Inject constructor() : ApiMapper<ApiDisgustResponse?, UIDisgust> {

    override fun mapToDomain(apiEntity: ApiDisgustResponse?): UIDisgust {
        return UIDisgust(
            id = apiEntity?.id ?: -1,
            name = apiEntity?.food.orEmpty(),
            imageUrl = apiEntity?.imageUrl.orEmpty(),
            isChecked = false
        )
    }
}
