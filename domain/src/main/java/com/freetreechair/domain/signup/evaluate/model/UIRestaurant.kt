package com.freetreechair.domain.signup.evaluate.model

data class UIRestaurant(
    val id: Int,
    val name: String,
    val category: String,
    val address: String,
    val imageUrl: String,
    val rating: Float = 0.0F,
    val isEvaluated: Boolean = false
)
