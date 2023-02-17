package com.freetreechair.navigator

import android.content.Context

interface MainNavigator {
    fun navigateMain(context: Context)
    fun navigateAuth(context: Context)
    fun transactionToHome()
}
