package com.freetreechair.data.preferences

interface PreferencesDataSource {
    fun getAccessToken(): String
    fun saveAccessToken(accessToken: String)
    fun saveNickname(nickname: String)
    fun saveDisgusts(disgusts: String)
    fun saveTastes(tastes: String)
    fun saveEvaluates(evaluates: String)
}
