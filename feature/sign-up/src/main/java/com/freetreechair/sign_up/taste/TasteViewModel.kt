package com.freetreechair.sign_up.taste

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.freetreechair.domain.taste.usecase.TasteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasteViewModel @Inject constructor(
    private val tasteUseCases: TasteUseCases
) : ViewModel() {

    private val _spicyValue = MutableLiveData(ZERO_FLOAT_NUMBER)
    val spicyValue: LiveData<Float> = _spicyValue

    private val _sugarValue = MutableLiveData(ZERO_FLOAT_NUMBER)
    val sugarValue: LiveData<Float> = _sugarValue

    private val _saltValue = MutableLiveData(ZERO_FLOAT_NUMBER)
    val saltValue: LiveData<Float> = _saltValue


    fun updateSpicyValue(value: Float) {
        _spicyValue.postValue(value)
    }

    fun updateSugarValue(value: Float) {
        _sugarValue.postValue(value)
    }

    fun updateSaltValue(value: Float) {
        _saltValue.postValue(value)
    }

    fun saveTastes() {
        val tastes = "${spicyValue.value},${sugarValue.value},${saltValue.value}"
        tasteUseCases.saveTasteUseCase(tastes)
    }

    companion object {
        const val ZERO_FLOAT_NUMBER = 0.0F
    }
}
