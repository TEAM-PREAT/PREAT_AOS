package com.freetreechair.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences
) : PreferencesDataSource {
    override fun getAccessToken(): String {
        return preferences.getString(PREAT_ACCESS_TOKEN, EMPTY_ACCESS_TOKEN).orEmpty()
    }

    override fun saveAccessToken(accessToken: String) {
        edit { putString(PREAT_ACCESS_TOKEN, accessToken) }
    }

    override fun saveNickname(nickname: String) {
        edit { putString(PREAT_USER_NICKNAME, nickname) }
    }

    override fun saveDisgusts(disgusts: String) {
        edit { putString(PREAT_USER_DISGUSTS, disgusts) }
    }

    override fun saveTastes(tastes: String) {
        edit { putString(PREAT_USER_TASTES, tastes) }
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }

    companion object {
        const val PREAT_ACCESS_TOKEN = "PREAT_ACCESS_TOKEN"
        const val EMPTY_ACCESS_TOKEN = ""
        const val PREAT_USER_NICKNAME = "PREAT_USER_NICKNAME"
        const val PREAT_USER_DISGUSTS = "PREAT_USER_DISGUSTS"
        const val PREAT_USER_TASTES = "PREAT_USER_TASTES"
    }
}
