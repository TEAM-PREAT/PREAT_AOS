package com.freetreechair.sign_up.nickname

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.common.extension.setQueryDebounce
import com.freetreechair.sign_up.R
import com.freetreechair.sign_up.databinding.FragmentNicknameBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern
import com.freetreechair.sign_up.nickname.NicknameState.NO_SPECIAL_CHARACTER
import com.freetreechair.sign_up.nickname.NicknameState.HAS_NO_STATE
import com.freetreechair.sign_up.nickname.NicknameState.OVER_TEXT_LIMIT
import io.reactivex.rxjava3.disposables.Disposable

@AndroidEntryPoint
class NicknameFragment : BindingFragment<FragmentNicknameBinding>(R.layout.fragment_nickname) {
    private val nicknameViewModel: NicknameViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = nicknameViewModel
        setStatusBarColorWhite()
        initEditTextFilter()
        initDuplicatedNicknameCheck()
        initNicknameStateUpdate()
        initButtonClickListener()
    }

    private fun initEditTextFilter() {
        binding.etNickname.filters = arrayOf(
            InputFilter { source, _, _, _, _, _ ->
                val noSpecialCharacterRegex = "^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣ㆍᆢ]*$"
                val noSpecialCharacterPattern = Pattern.compile(noSpecialCharacterRegex)
                val isPossibleChar =
                    source.isNullOrEmpty() || noSpecialCharacterPattern.matcher(source).matches()
                if (!isPossibleChar) nicknameViewModel.updateNicknameState(NO_SPECIAL_CHARACTER)
                return@InputFilter if (isPossibleChar) source else ""
            },
            InputFilter.LengthFilter(12)
        )
    }

    private fun initDuplicatedNicknameCheck() {
        val profileIdEditTextSubscription: Disposable =
            binding.etNickname.setQueryDebounce(object :
                    (String) -> Unit {
                override fun invoke(it: String) {
                    if (it.isNotEmpty()) nicknameViewModel.checkDuplicateNickname()
                }
            })
        nicknameViewModel.addDisposable(profileIdEditTextSubscription)
    }

    private fun initNicknameStateUpdate() {
        binding.etNickname.addTextChangedListener {
            val message = when (binding.etNickname.text?.length) {
                in 0..11 -> HAS_NO_STATE
                12 -> OVER_TEXT_LIMIT
                else -> throw IllegalStateException("Maximum nickname length is 10")
            }
            nicknameViewModel.updateNicknameState(message)
        }
    }

    private fun initButtonClickListener() {
        binding.btnOk.setOnClickListener {
            findNavController().navigate(
                NicknameFragmentDirections.actionNicknameFragmentToWelcomeFragment(
                    nicknameViewModel.nickname.value.orEmpty()
                )
            )
        }
    }
}
