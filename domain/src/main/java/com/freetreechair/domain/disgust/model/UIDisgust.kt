package com.freetreechair.domain.disgust.model

data class UIDisgust(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isChecked: Boolean = false
)
