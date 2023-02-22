package com.freetreechair.domain.disgust.usecase

import com.freetreechair.domain.disgust.repository.DisgustRepository

class SaveDisgustUseCase(
    private val repository: DisgustRepository
) {
    operator fun invoke(disgusts: List<Int>) {
        
    }
}
