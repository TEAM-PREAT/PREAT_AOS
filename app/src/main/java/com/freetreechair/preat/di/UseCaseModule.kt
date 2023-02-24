package com.freetreechair.preat.di

import com.freetreechair.domain.signup.disgust.repository.DisgustRepository
import com.freetreechair.domain.signup.disgust.usecase.DisgustUseCases
import com.freetreechair.domain.signup.disgust.usecase.FetchDisgustUseCase
import com.freetreechair.domain.signup.disgust.usecase.SaveDisgustUseCase
import com.freetreechair.domain.signup.evaluate.repository.EvaluateRepository
import com.freetreechair.domain.signup.evaluate.usecase.EvaluateUseCases
import com.freetreechair.domain.signup.evaluate.usecase.FetchRestaurantUseCase
import com.freetreechair.domain.signup.evaluate.usecase.SaveEvaluateUseCase
import com.freetreechair.domain.auth.login.repository.LoginRepository
import com.freetreechair.domain.auth.login.usecase.GetAccessTokenUseCase
import com.freetreechair.domain.auth.login.usecase.LoginUseCases
import com.freetreechair.domain.auth.login.usecase.MakeLoginRequestUseCase
import com.freetreechair.domain.auth.login.usecase.SaveAccessTokenUseCase
import com.freetreechair.domain.signup.complete.repository.CompleteRepository
import com.freetreechair.domain.signup.complete.usecase.CompleteUseCases
import com.freetreechair.domain.signup.complete.usecase.GetUserIdentificationUseCase
import com.freetreechair.domain.signup.complete.usecase.MakeSignUpRequestUseCase
import com.freetreechair.domain.signup.nickname.repository.NicknameRepository
import com.freetreechair.domain.signup.nickname.usecase.CheckIsNicknameDuplicatedUseCase
import com.freetreechair.domain.signup.nickname.usecase.NicknameUseCases
import com.freetreechair.domain.signup.nickname.usecase.SaveNicknameUseCase
import com.freetreechair.domain.signup.taste.repository.TasteRepository
import com.freetreechair.domain.signup.taste.usecase.SaveTasteUseCase
import com.freetreechair.domain.signup.taste.usecase.TasteUseCases
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

    @ViewModelScoped
    @Provides
    fun providesEvaluateUseCase(repository: EvaluateRepository): EvaluateUseCases {
        return EvaluateUseCases(
            fetchRestaurantUseCase = FetchRestaurantUseCase(repository),
            saveEvaluateUseCase = SaveEvaluateUseCase(repository)
        )
    }

    @ViewModelScoped
    @Provides
    fun providesCompleteUseCase(repository: CompleteRepository): CompleteUseCases {
        return CompleteUseCases(
            getUserIdentificationUseCase = GetUserIdentificationUseCase(repository),
            makeSignUpRequestUseCase = MakeSignUpRequestUseCase(repository)
        )
    }
}
