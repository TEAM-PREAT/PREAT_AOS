package com.freetreechair.sign_up.evaluate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.freetreechair.common.base.BaseViewModel
import com.freetreechair.common.util.UiState
import com.freetreechair.common.util.let2
import com.freetreechair.domain.evaluate.model.UIRestaurant
import com.freetreechair.domain.evaluate.usecase.EvaluateUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EvaluateViewModel @Inject constructor(
    private val evaluateUseCases: EvaluateUseCases
) : BaseViewModel() {
    var query = MutableLiveData("")
    var isButtonClickable = MutableLiveData(false)

    private var restaurantState by mutableStateOf<List<UIRestaurant>>(
        emptyList()
    )

    private val _restaurantUiState: MutableStateFlow<UiState<List<UIRestaurant>>> = MutableStateFlow(UiState.Loading)
    val restaurantUiState: StateFlow<UiState<List<UIRestaurant>>>
        get() = _restaurantUiState.asStateFlow()

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchRestaurants()
    }

    fun fetchRestaurants() {
        viewModelScope.launch {
            _restaurantUiState.value = UiState.Loading
            _isLoading.value = true
            evaluateUseCases.fetchRestaurantUseCase(
                query = query.value
            ).onSuccess { response ->
                restaurantState = if (restaurantState.isEmpty()) {
                    response
                } else {
                    val old = restaurantState.filter { it.isEvaluated }
                    val oldIds = mutableSetOf<Int>()
                    old.forEach {
                        oldIds.add(it.id)
                    }
                    val new = response.filter { it.id !in oldIds }
                    old.union(new).toList()
                }
                _restaurantUiState.value = UiState.Success(restaurantState)
                _isLoading.value = false
            }.onFailure {
                _restaurantUiState.value = UiState.Failure(it.message)
                _isLoading.value = false
            }
        }
    }

    fun updateRestaurants(restaurantId: Int?, restaurantRating: Float?) {
        let2(restaurantId, restaurantRating) { id, rating ->
            restaurantState = restaurantState.map {
                if (it.id == id) {
                    it.copy(
                        isEvaluated = rating != 0.0F,
                        rating = rating
                    )
                } else it
            }
            _restaurantUiState.value = UiState.Success(restaurantState)
        }
        isButtonClickable.value = restaurantState.any { it.isEvaluated }
    }

    fun saveEvaluates() {
        val stringBuilder = StringBuilder()
        restaurantState.forEach { restaurant ->
            if (restaurant.isEvaluated) stringBuilder.append("${restaurant.id}-${restaurant.rating},")
        }
        evaluateUseCases.saveEvaluateUseCase(stringBuilder.toString())
    }
}
