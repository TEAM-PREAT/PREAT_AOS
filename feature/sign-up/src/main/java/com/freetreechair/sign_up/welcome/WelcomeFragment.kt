package com.freetreechair.sign_up.welcome

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.sign_up.R
import com.freetreechair.sign_up.databinding.FragmentWelcomeBinding

class WelcomeFragment : BindingFragment<FragmentWelcomeBinding>(R.layout.fragment_welcome) {
    private val args: WelcomeFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNickname.text = args.nickname
    }
}
