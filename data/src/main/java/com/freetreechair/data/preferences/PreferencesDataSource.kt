package com.freetreechair.data.preferences

interface PreferencesDataSource {
    fun getAccessToken(): String
    fun saveAccessToken(accessToken: String)
}
