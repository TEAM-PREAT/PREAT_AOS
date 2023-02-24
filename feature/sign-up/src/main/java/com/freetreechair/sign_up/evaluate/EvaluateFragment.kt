package com.freetreechair.sign_up.evaluate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.common.extension.setOnSingleClickListener
import com.freetreechair.common.extension.setQueryDebounce
import com.freetreechair.common.extension.shortToast
import com.freetreechair.common.ui.component.PreatProgressBar
import com.freetreechair.common.util.UiState
import com.freetreechair.sign_up.R
import com.freetreechair.sign_up.databinding.FragmentEvaluateBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class EvaluateFragment : BindingFragment<FragmentEvaluateBinding>(R.layout.fragment_evaluate) {
    private val evaluateViewModel: EvaluateViewModel by activityViewModels()
    private val evaluateAdapter = EvaluateAdapter(::onEvaluate)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState).apply {
            binding.cvProgress.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    PreatProgressBar()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = evaluateViewModel
        setupUI()
        observeRestaurants()
        updateRestaurantsAsInputQuery()
        initButtonClickListener()
    }

    private fun setupUI() {
        setStatusBarColorWhite()
        with(binding.rvEvaluate) {
            adapter = evaluateAdapter
            itemAnimator = null
        }
    }

    private fun observeRestaurants() {
        evaluateViewModel.restaurantUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    evaluateAdapter.submitList(it.data)
                    evaluateViewModel.updateRestaurants(restaurantId = null, restaurantRating = null)
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

    private fun updateRestaurantsAsInputQuery() {
        val queryEditTextSubscription: Disposable =
            binding.etSearch.setQueryDebounce(object :
                    (String) -> Unit {
                override fun invoke(it: String) {
                    evaluateViewModel.fetchRestaurants()
                }
            })
        evaluateViewModel.addDisposable(queryEditTextSubscription)
    }

    private fun onEvaluate(restaurantId: Int, rating: Float) {
        evaluateViewModel.updateRestaurants(restaurantId, rating)
    }

    private fun initButtonClickListener() {
        with(binding) {
            btnOk.setOnClickListener {
                evaluateViewModel.saveEvaluates()
                findNavController().navigate(R.id.action_evaluateFragment_to_completeFragment)
            }
            btnDelete.setOnSingleClickListener {
                etSearch.text = null
                etSearch.clearFocus()
            }
        }
    }
}
