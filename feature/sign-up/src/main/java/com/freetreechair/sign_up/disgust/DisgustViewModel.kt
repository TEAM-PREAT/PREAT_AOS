package com.freetreechair.sign_up.disgust

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.freetreechair.common.base.BaseViewModel
import com.freetreechair.common.util.UiState
import com.freetreechair.domain.disgust.model.UIDisgust
import com.freetreechair.domain.disgust.usecase.DisgustUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisgustViewModel @Inject constructor(
    private val disgustUseCases: DisgustUseCases
) : BaseViewModel() {
    var query = MutableLiveData("")
    var isButtonClickable = MutableLiveData(false)

    var state by mutableStateOf<List<UIDisgust>>(
        emptyList()
    )
        private set

    private val _disgustUiState: MutableStateFlow<UiState<List<UIDisgust>>> = MutableStateFlow(UiState.Loading)
    val disgustUiState: StateFlow<UiState<List<UIDisgust>>>
        get() = _disgustUiState.asStateFlow()

    fun fetchDisgusts() {
        viewModelScope.launch {
            _disgustUiState.value = UiState.Loading
            disgustUseCases.fetchDisgustUseCase(
                query = query.value
            ).onSuccess {
                state = it
                _disgustUiState.value = UiState.Success(it)
            }.onFailure {
                _disgustUiState.value = UiState.Failure(it.message)
            }
        }
    }

    fun updateDisgusts(id: Int) {
        state = state.map {
            if (it.id == id) {
                it.copy(
                    isChecked = !it.isChecked
                )
            } else it
        }
        isButtonClickable.value = state.any { it.isChecked }
        _disgustUiState.value = UiState.Success(state)
    }

    fun saveDisgusts(): String {
        val stringBuilder = StringBuilder()
        state.forEach { uiDisgust ->
            if (uiDisgust.isChecked) stringBuilder.append("${uiDisgust.id},")
        }
        disgustUseCases.saveDisgustUseCase(stringBuilder.toString())
        return stringBuilder.toString()
    }
}
