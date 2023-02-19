package com.freetreechair.preat.di

import com.freetreechair.domain.login.repository.LoginRepository
import com.freetreechair.domain.login.usecase.GetAccessTokenUseCase
import com.freetreechair.domain.login.usecase.LoginUseCases
import com.freetreechair.domain.login.usecase.MakeLoginRequestUseCase
import com.freetreechair.domain.login.usecase.SaveAccessTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideLoginUseCases(repository: LoginRepository): LoginUseCases {
        return LoginUseCases(
            getAccessTokenUseCase = GetAccessTokenUseCase(repository),
            saveAccessTokenUseCase = SaveAccessTokenUseCase(repository),
            makeLoginRequestUseCase = MakeLoginRequestUseCase(repository)
        )
    }
}
