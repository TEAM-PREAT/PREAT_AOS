package com.freetreechair.preat

import android.app.Application
import android.content.Context
import com.freetreechair.common.util.PreatDebugTree
import com.freetreechair.preat.BuildConfig.KAKAO_NATIVE_APP_KEY
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PreatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKakaoSdk(this)
    }
}

private fun initTimber() {
    if (BuildConfig.DEBUG) {
        Timber.plant(PreatDebugTree())
    }
}

private fun initKakaoSdk(context: Context) {
    KakaoSdk.init(context, KAKAO_NATIVE_APP_KEY)
}
