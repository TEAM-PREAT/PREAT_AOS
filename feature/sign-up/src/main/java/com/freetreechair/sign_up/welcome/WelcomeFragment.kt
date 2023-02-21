package com.freetreechair.sign_up.welcome

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.navigation.fragment.navArgs
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.sign_up.R
import com.freetreechair.sign_up.databinding.FragmentWelcomeBinding

class WelcomeFragment : BindingFragment<FragmentWelcomeBinding>(R.layout.fragment_welcome) {
    private val args: WelcomeFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNickname.text = args.nickname
        setAnimationOnCard()
    }

    private fun setAnimationOnCard() {
        ObjectAnimator.ofFloat(binding.clWelcome, "translationY", TRANSLATION_VALUE).apply {
            interpolator = LinearInterpolator()
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            duration = DURATION_TIME
            start()
        }
    }

    companion object {
        const val DURATION_TIME = 3000L
        const val TRANSLATION_VALUE = -50F
    }
}
