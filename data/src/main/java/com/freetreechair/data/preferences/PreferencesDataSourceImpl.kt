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

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }

    companion object {
        const val PREAT_ACCESS_TOKEN = "PREAT_ACCESS_TOKEN"
        const val EMPTY_ACCESS_TOKEN = ""
    }
}
