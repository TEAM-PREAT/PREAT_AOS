package com.freetreechair.data.login.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiLoginResponse(
    @field:Json(name = "isNewUser") val isNewUser: Boolean?,
    @field:Json(name = "accessToken") val accessToken: String?
)
