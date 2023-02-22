package com.freetreechair.sign_up.disgust

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {
    var query = MutableLiveData("")

    private val _disgustUiState: MutableStateFlow<UiState<List<UIDisgust>>> = MutableStateFlow(UiState.Loading)
    val disgustUiState: StateFlow<UiState<List<UIDisgust>>>
        get() = _disgustUiState.asStateFlow()

    fun fetchDisgusts() {
        viewModelScope.launch {
            _disgustUiState.value = UiState.Loading
            disgustUseCases.fetchDisgustUseCase(
                query = query.value
            ).onSuccess {
                _disgustUiState.value = UiState.Success(it)
            }.onFailure {
                _disgustUiState.value = UiState.Failure(it.message)
            }
        }
    }
}
