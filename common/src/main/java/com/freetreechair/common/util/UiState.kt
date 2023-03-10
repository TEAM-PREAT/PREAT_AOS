package com.freetreechair.common.util

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : UiState<T>

    data class Failure(
        val message: String?,
    ) : UiState<Nothing>
}
