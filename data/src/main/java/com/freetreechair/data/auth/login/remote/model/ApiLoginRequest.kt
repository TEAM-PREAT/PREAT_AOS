package com.freetreechair.data.auth.login.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiLoginRequest(
    @field:Json(name = "platform") val platform: String,
    @field:Json(name = "socialToken") val socialToken: String
)
