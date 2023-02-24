package com.freetreechair.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.freetreechair.auth.util.KakaoLoginManager
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.common.extension.setOnSingleClickListener
import com.freetreechair.common.util.EventObserver
import com.freetreechair.preat.auth.R
import com.freetreechair.preat.auth.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    @Inject
    lateinit var kakaoLoginManager: KakaoLoginManager

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLoginButtonClick()
        initLoginObserver()
        initLoginFailureMessageObserver()
        initMoveToHomeObserver()
        initMoveToSignUpObserver()
    }

    private fun onLoginButtonClick() {
        with(binding) {
            clKakao.setOnSingleClickListener {
                loginViewModel.updatePlatform(KAKAO)
                kakaoLoginManager.startKakaoLogin {
                    loginViewModel.updateSocialToken(it)
                }
            }
        }
    }

    private fun initLoginObserver() {
        loginViewModel.socialToken.observe(viewLifecycleOwner) {
            loginViewModel.postLogin()
        }
    }

    private fun initLoginFailureMessageObserver() {
        loginViewModel.loginFailureMessage.observe(viewLifecycleOwner) {
            Timber.d("로그인 실패")
        }
    }

    private fun initMoveToHomeObserver() {
        loginViewModel.navigateToHome.observe(
            viewLifecycleOwner,
            EventObserver {
                navigateMainActivity()
            }
        )
    }

    private fun navigateMainActivity() {
        mainNavigator.navigateMain(requireActivity())
        requireActivity().finish()
    }

    private fun initMoveToSignUpObserver() {
        loginViewModel.navigateToSignUp.observe(
            viewLifecycleOwner,
            EventObserver {
                navigateSignUpActivity(
                    it
                )
            }
        )
    }

    private fun navigateSignUpActivity(platform: String) {
        mainNavigator.navigateSignUp(
            context = requireContext(),
            platform = Pair("platform", platform)
        )
        requireActivity().finish()
    }

    companion object {
        const val KAKAO = "kakao"
    }
}
