package com.freetreechair.sign_up.disgust

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.common.extension.setOnSingleClickListener
import com.freetreechair.common.extension.setQueryDebounce
import com.freetreechair.common.extension.shortToast
import com.freetreechair.common.util.UiState
import com.freetreechair.sign_up.R
import com.freetreechair.sign_up.databinding.FragmentDisgustBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DisgustFragment : BindingFragment<FragmentDisgustBinding>(R.layout.fragment_disgust) {
    private val disgustAdapter = DisgustAdapter(::onDisgustClick)
    private val disgustViewModel: DisgustViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = disgustViewModel
        setupUI()
        observeDisgusts()
        updateDisgustsAsInputQuery()
        initButtonClickListener()
    }

    private fun setupUI() {
        setStatusBarColorWhite()
        binding.rvDisgust.adapter = disgustAdapter
    }

    private fun observeDisgusts() {
        disgustViewModel.disgustUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    disgustAdapter.submitList(it.data)
                    disgustViewModel.updateDisgusts(disgustId = null)
                }
                is UiState.Failure -> {
                    it.message?.let { msg ->
                        requireContext().shortToast(msg)
                    }
                }
                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun updateDisgustsAsInputQuery() {
        val queryEditTextSubscription: Disposable =
            binding.etSearch.setQueryDebounce(object :
                    (String) -> Unit {
                override fun invoke(it: String) {
                    disgustViewModel.fetchDisgusts()
                }
            })
        disgustViewModel.addDisposable(queryEditTextSubscription)
    }

    private fun onDisgustClick(disgustId: Int) {
        disgustViewModel.updateDisgusts(disgustId)
    }

    private fun initButtonClickListener() {
        with(binding) {
            btnOk.setOnClickListener {
                disgustViewModel.saveDisgusts()
                findNavController().navigate(R.id.action_disgustFragment_to_tasteFragment)
            }
            btnDelete.setOnSingleClickListener {
                etSearch.text = null
                etSearch.clearFocus()
            }
        }
    }
}
