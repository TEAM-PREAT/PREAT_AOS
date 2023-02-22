package com.freetreechair.sign_up.disgust

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.common.component.PreatProgressBar
import com.freetreechair.common.extension.shortToast
import com.freetreechair.common.util.UiState
import com.freetreechair.sign_up.R
import com.freetreechair.sign_up.databinding.FragmentDisgustBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DisgustFragment : BindingFragment<FragmentDisgustBinding>(R.layout.fragment_disgust) {
    private val disgustAdapter = DisgustAdapter(::onClick)
    private val disgustViewModel: DisgustViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeDisgusts()
    }

    private fun setupUI() {
        binding.rvDisgust.adapter = disgustAdapter
    }

    private fun observeDisgusts() {
        disgustViewModel.disgustUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    disgustAdapter.submitList(it.data)
                }
                is UiState.Failure -> {
                    it.msg?.let { msg ->
                        requireContext().shortToast(msg)
                    }
                }
                else -> {
                    binding.cvProgressBar.apply {
                        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                        setContent {
                            PreatProgressBar()
                        }
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onClick(id: Int) {

    }
}
