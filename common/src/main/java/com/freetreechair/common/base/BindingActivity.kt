package com.freetreechair.common.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.freetreechair.common.util.EventObserver
import com.freetreechair.common.util.Injector
import com.freetreechair.navigator.MainNavigator
import dagger.hilt.android.EntryPointAccessors

abstract class BindingActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    val mainNavigator: MainNavigator by lazy {
        EntryPointAccessors.fromActivity(
            this,
            Injector.MainNavigatorInjector::class.java
        ).mainNavigator()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        EntryPointAccessors.fromActivity(
            this,
            Injector.SharedPreferencesInjector::class.java
        ).sharedPreferences()
    }

    protected fun terminationTokenHandling(viewModel: BaseViewModel) {
        viewModel.moveToLogin.observe(this, EventObserver {
            mainNavigator.navigateAuth(this)
            sharedPreferences.edit().remove("PREAT_ACCESS_TOKEN").apply()
            finishAffinity()
        })
    }
}
