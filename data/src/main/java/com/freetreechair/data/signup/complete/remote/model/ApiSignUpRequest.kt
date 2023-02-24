package com.freetreechair.data.signup.complete.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiSignUpRequest(
    @field:Json(name = "nickname") val nickname: String,
    @field:Json(name = "sweet") val sweet: Int,
    @field:Json(name = "spicy") val spicy: Int,
    @field:Json(name = "salty") val salty: Int,
    @field:Json(name = "hateFoods") val hateFoods: List<Int>,
    @field:Json(name = "reviews") val reviews: List<ApiRestaurantRating>
)

@JsonClass(generateAdapter = true)
data class ApiRestaurantRating(
    @field:Json(name = "restaurantId") val restaurantId: String,
    @field:Json(name = "rating") val rating: Double
)


