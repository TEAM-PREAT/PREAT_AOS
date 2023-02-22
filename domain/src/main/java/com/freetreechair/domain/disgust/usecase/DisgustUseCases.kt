package com.freetreechair.domain.disgust.usecase

data class DisgustUseCases(
    val fetchDisgustUseCase: FetchDisgustUseCase,
    val selectDisgustUseCase: SelectDisgustUseCase,
    val saveDisgustUseCase: SaveDisgustUseCase
)
