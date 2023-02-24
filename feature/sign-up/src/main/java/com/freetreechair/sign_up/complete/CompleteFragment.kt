package com.freetreechair.sign_up.complete

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.fragment.app.activityViewModels
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.common.extension.shortToast
import com.freetreechair.common.util.EventObserver
import com.freetreechair.sign_up.R
import com.freetreechair.sign_up.databinding.FragmentCompleteBinding
import com.freetreechair.sign_up.welcome.WelcomeFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CompleteFragment : BindingFragment<FragmentCompleteBinding>(R.layout.fragment_complete) {
    private val completeViewModel: CompleteViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        completeViewModel.getUserIdentification()
        setStatusBarColorOrange()
        setAnimationOnImage()
        initButtonClickListener()
        initSignUpObserver()
        initSignUpFailureMessageObserver()
    }

    private fun setAnimationOnImage() {
        ObjectAnimator.ofFloat(binding.ivComplete, "translationY", WelcomeFragment.TRANSLATION_VALUE).apply {
            interpolator = LinearInterpolator()
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            duration = WelcomeFragment.DURATION_TIME
            start()
        }
    }

    private fun initButtonClickListener() {
        binding.btnOk.setOnClickListener {
            completeViewModel.postSignUp()
        }
    }

    private fun initSignUpObserver() {
        completeViewModel.navigateToHome.observe(
            viewLifecycleOwner,
            EventObserver {
                navigateMainActivity()
            }
        )
    }

    private fun initSignUpFailureMessageObserver() {
        completeViewModel.signUpFailureMessage.observe(viewLifecycleOwner) {
            requireActivity().shortToast(it)
            Timber.tag("logging").d("회원가입 실패")
        }
    }

    private fun navigateMainActivity() {
        mainNavigator.navigateMain(requireActivity())
        requireActivity().finish()
    }
}
