package com.freetreechair.preat.navigator

import android.content.Context
import com.freetreechair.auth.AuthActivity
import com.freetreechair.common.extension.navigateActivity
import com.freetreechair.navigator.MainNavigator
import com.freetreechair.preat.MainActivity
import com.freetreechair.sign_up.SignUpActivity
import javax.inject.Inject

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateMain(context: Context) {
        context.navigateActivity<MainActivity>()
    }

    override fun navigateAuth(context: Context) {
        context.navigateActivity<AuthActivity>()
    }

    override fun navigateSignUp(context: Context, platform: Pair<String, String>) {
        context.navigateActivity<SignUpActivity>(platform)
    }

    override fun transactionToHome() {
        MainActivity.transactionToHome()
    }
}
