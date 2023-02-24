package com.freetreechair.data.signup.evaluate.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiRestaurantResponse(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "address") val address: String?,
    @field:Json(name = "imageUrl") val imageUrl: String?,
    @field:Json(name = "category") val category: String?,
    @field:Json(name = "latitude") val latitude: Double?,
    @field:Json(name = "longitude") val longitude: Double?,
    @field:Json(name = "rating") val rating: ApiRating?,
)

@JsonClass(generateAdapter = true)
data class ApiRating(
    @field:Json(name = "hasPredict") val hasPredict: Boolean?,
    @field:Json(name = "value") val value: Double?
)
