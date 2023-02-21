package com.freetreechair.data.nickname.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiNicknameResponse(
    @field:Json(name = "isAvailable") val isAvailable: Boolean?
)
