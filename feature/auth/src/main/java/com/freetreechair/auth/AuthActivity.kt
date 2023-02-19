package com.freetreechair.auth

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import androidx.activity.viewModels
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.freetreechair.auth.login.AutoLoginState
import com.freetreechair.auth.login.LoginFragment
import com.freetreechair.auth.login.LoginViewModel
import com.freetreechair.common.base.BindingActivity
import com.freetreechair.common.extension.replace
import com.freetreechair.preat.auth.R
import com.freetreechair.preat.auth.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BindingActivity<ActivityAuthBinding>(R.layout.activity_auth) {
    private val loginViewModel: LoginViewModel by viewModels()
    lateinit var autoLoginState: AutoLoginState

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        checkAutoLoginState()
        autoLogin()
    }

    private fun autoLogin() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (::autoLoginState.isInitialized) {
                        if (autoLoginState == AutoLoginState.AUTO_LOGIN_SUCCESS) {
                            mainNavigator.navigateMain(this@AuthActivity)
                            finish()
                        } else {
                            replace<LoginFragment>(R.id.container_auth)
                        }
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun checkAutoLoginState() {
        autoLoginState = if (loginViewModel.getAccessToken().isNotEmpty()) {
            AutoLoginState.AUTO_LOGIN_SUCCESS
        } else {
            AutoLoginState.AUTO_LOGIN_FAILURE
        }
    }

    private fun setAnimationOnSplash() {
        if (isOverAndroid12) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f).apply {
                    interpolator = AccelerateInterpolator()
                    duration = SPLASH_TIME
                    doOnEnd { splashScreenView.remove() }
                    start()
                }
            }
        }
    }

    private fun setDelayIfDeviceIsOverAndroid12(objectAnimator: ObjectAnimator) {
        if (isOverAndroid12) {
            objectAnimator.startDelay = SPLASH_TIME + 300L
        }
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    private val isOverAndroid12 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    companion object {
        const val SPLASH_TIME = 300L
    }
}
