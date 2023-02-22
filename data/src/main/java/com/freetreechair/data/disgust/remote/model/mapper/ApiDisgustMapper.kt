package com.freetreechair.data.disgust.remote.model.mapper

import com.freetreechair.data.ApiMapper
import com.freetreechair.data.disgust.remote.model.ApiDisgustResponse
import com.freetreechair.domain.disgust.model.UIDisgust
import javax.inject.Inject

class ApiDisgustMapper @Inject constructor() : ApiMapper<ApiDisgustResponse?, UIDisgust> {

    override fun mapToDomain(apiEntity: ApiDisgustResponse?): UIDisgust {
        return UIDisgust(
            id = apiEntity?.id ?: -1,
            name = apiEntity?.name.orEmpty(),
            imageUrl = apiEntity?.imageUrl.orEmpty(),
            isChecked = false
        )
    }
}
