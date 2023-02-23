package com.freetreechair.sign_up.taste

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.freetreechair.common.base.BindingFragment
import com.freetreechair.common.extension.setOnSingleClickListener
import com.freetreechair.sign_up.R
import com.freetreechair.sign_up.databinding.FragmentTasteBinding

class TasteFragment : BindingFragment<FragmentTasteBinding>(R.layout.fragment_taste) {
    private val tasteViewModel: TasteViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = tasteViewModel
        observeSlider()
        initButtonClickListener()
    }

    private fun observeSlider() {
        with(binding) {
            slSpicy.addOnChangeListener { _, value, _ ->
                tasteViewModel.updateSpicyValue(value / 100)
            }
            slSugar.addOnChangeListener { _, value, _ ->
                tasteViewModel.updateSugarValue(value / 100)
            }
            slSalt.addOnChangeListener { _, value, _ ->
                tasteViewModel.updateSaltValue(value / 100)
            }
        }
    }

    private fun initButtonClickListener() {
        binding.btnOk.setOnSingleClickListener {
            tasteViewModel.saveTastes()
            findNavController().navigate(R.id.action_tasteFragment_to_evaluateFragment)
        }
    }
}
