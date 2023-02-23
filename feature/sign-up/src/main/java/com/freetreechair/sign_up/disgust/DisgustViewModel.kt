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

    init {
        fetchDisgusts()
    }

    fun fetchDisgusts() {
        viewModelScope.launch {
            _disgustUiState.value = UiState.Loading
            disgustUseCases.fetchDisgustUseCase(
                query = query.value
            ).onSuccess { response ->
                state = if (state.isEmpty()) {
                    response
                } else {
                    val old = state.filter { it.isChecked }
                    val oldIds = mutableSetOf<Int>()
                    old.forEach {
                        oldIds.add(it.id)
                    }
                    val new = response.filter { it.id !in oldIds }
                    old.union(new).toList()
                }
                _disgustUiState.value = UiState.Success(state)
            }.onFailure {
                _disgustUiState.value = UiState.Failure(it.message)
            }
        }
    }

    fun updateDisgusts(disgustId: Int?) {
        disgustId?.let { id ->
            state = state.map {
                if (it.id == id) {
                    it.copy(
                        isChecked = !it.isChecked
                    )
                } else it
            }
            _disgustUiState.value = UiState.Success(state)
        }
        isButtonClickable.value = state.any { it.isChecked }
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
