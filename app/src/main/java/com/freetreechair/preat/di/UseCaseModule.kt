package com.freetreechair.preat.di

import com.freetreechair.domain.disgust.repository.DisgustRepository
import com.freetreechair.domain.disgust.usecase.DisgustUseCases
import com.freetreechair.domain.disgust.usecase.FetchDisgustUseCase
import com.freetreechair.domain.disgust.usecase.SaveDisgustUseCase
import com.freetreechair.domain.login.repository.LoginRepository
import com.freetreechair.domain.login.usecase.GetAccessTokenUseCase
import com.freetreechair.domain.login.usecase.LoginUseCases
import com.freetreechair.domain.login.usecase.MakeLoginRequestUseCase
import com.freetreechair.domain.login.usecase.SaveAccessTokenUseCase
import com.freetreechair.domain.nickname.repository.NicknameRepository
import com.freetreechair.domain.nickname.usecase.CheckIsNicknameDuplicatedUseCase
import com.freetreechair.domain.nickname.usecase.NicknameUseCases
import com.freetreechair.domain.nickname.usecase.SaveNicknameUseCase
import com.freetreechair.domain.taste.repository.TasteRepository
import com.freetreechair.domain.taste.usecase.SaveTasteUseCase
import com.freetreechair.domain.taste.usecase.TasteUseCases
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
    fun providesLoginUseCases(repository: LoginRepository): LoginUseCases {
        return LoginUseCases(
            getAccessTokenUseCase = GetAccessTokenUseCase(repository),
            saveAccessTokenUseCase = SaveAccessTokenUseCase(repository),
            makeLoginRequestUseCase = MakeLoginRequestUseCase(repository)
        )
    }

    @ViewModelScoped
    @Provides
    fun providesNicknameUseCase(repository: NicknameRepository): NicknameUseCases {
        return NicknameUseCases(
            checkIsNicknameDuplicatedUseCase = CheckIsNicknameDuplicatedUseCase(repository),
            saveNicknameUseCase = SaveNicknameUseCase(repository)
        )
    }

    @ViewModelScoped
    @Provides
    fun providesDisgustUseCase(repository: DisgustRepository): DisgustUseCases {
        return DisgustUseCases(
            fetchDisgustUseCase = FetchDisgustUseCase(repository),
            saveDisgustUseCase = SaveDisgustUseCase(repository)
        )
    }

    @ViewModelScoped
    @Provides
    fun providesTasteUseCase(repository: TasteRepository): TasteUseCases {
        return TasteUseCases(
            saveTasteUseCase = SaveTasteUseCase(repository)
        )
    }
}
