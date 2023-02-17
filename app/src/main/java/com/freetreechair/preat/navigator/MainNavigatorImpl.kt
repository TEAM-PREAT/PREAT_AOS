package com.freetreechair.preat.navigator

import android.content.Context
import com.freetreechair.common.extension.navigateActivity
import com.freetreechair.navigator.MainNavigator
import com.freetreechair.preat.MainActivity
import javax.inject.Inject

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateMain(context: Context) {
        context.navigateActivity<MainActivity>()
    }

    override fun navigateAuth(context: Context) {
        // TODO AuthActivity 이동!
    }

    override fun transactionToHome() {
        MainActivity.transactionToHome()
    }
}
