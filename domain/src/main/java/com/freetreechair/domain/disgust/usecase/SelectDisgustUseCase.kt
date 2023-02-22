package com.freetreechair.domain.disgust.usecase

import com.freetreechair.domain.disgust.repository.DisgustRepository

class SelectDisgustUseCase(
    private val repository: DisgustRepository
) {
    operator fun invoke(disgustId: Int) {

    }
}
