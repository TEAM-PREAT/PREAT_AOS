package com.freetreechair.data.signup.complete.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiSignUpResponse(
    @field:Json(name = "userId") val userId: Int?,
    @field:Json(name = "accessToken") val accessToken: String?
)
