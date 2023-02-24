package com.freetreechair.common.base

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.freetreechair.common.R
import com.freetreechair.common.util.EventObserver
import com.freetreechair.common.util.Injector
import com.freetreechair.navigator.MainNavigator
import dagger.hilt.android.EntryPointAccessors

abstract class BindingFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    private var _binding: T? = null
    protected val binding: T
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    val mainNavigator: MainNavigator by lazy {
        EntryPointAccessors.fromActivity(
            requireActivity(),
            Injector.MainNavigatorInjector::class.java
        ).mainNavigator()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        EntryPointAccessors.fromActivity(
            requireActivity(),
            Injector.SharedPreferencesInjector::class.java
        ).sharedPreferences()
    }

    fun terminationTokenHandling(viewModel: BaseViewModel) {
        viewModel.moveToLogin.observe(viewLifecycleOwner, EventObserver {
            mainNavigator.navigateAuth(requireActivity())
            sharedPreferences.edit().remove("PREAT_ACCESS_TOKEN").apply()
            requireActivity().finish()
        })
    }

    @Suppress("Deprecation")
    fun setStatusBarColorOrange() {
        requireActivity().window.apply {
            statusBarColor = ContextCompat.getColor(requireActivity(), R.color.splash_orange)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                WindowInsetsControllerCompat(this, this@BindingFragment.requireView()).isAppearanceLightStatusBars =
                    false
            } else {
                this.decorView.systemUiVisibility = 0
            }
        }
    }

    @Suppress("Deprecation")
    fun setStatusBarColorWhite() {
        requireActivity().window.apply {
            statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                WindowInsetsControllerCompat(this, this@BindingFragment.requireView()).isAppearanceLightStatusBars =
                    true
            } else {
                this.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
