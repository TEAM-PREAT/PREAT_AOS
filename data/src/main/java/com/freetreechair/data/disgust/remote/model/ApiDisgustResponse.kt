package com.freetreechair.data.disgust.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiDisgustResponse(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "food") val name: String?,
    @field:Json(name = "imageUrl") val imageUrl: String?
)
