package com.freetreechair.preat.di

import com.freetreechair.data.signup.disgust.repository.DisgustRepositoryImpl
import com.freetreechair.data.signup.evaluate.repository.EvaluateRepositoryImpl
import com.freetreechair.data.auth.login.repository.LoginRepositoryImpl
import com.freetreechair.data.signup.complete.repository.CompleteRepositoryImpl
import com.freetreechair.data.signup.nickname.repository.NicknameRepositoryImpl
import com.freetreechair.data.signup.taste.repository.TasteRepositoryImpl
import com.freetreechair.domain.signup.disgust.repository.DisgustRepository
import com.freetreechair.domain.signup.evaluate.repository.EvaluateRepository
import com.freetreechair.domain.auth.login.repository.LoginRepository
import com.freetreechair.domain.signup.complete.repository.CompleteRepository
import com.freetreechair.domain.signup.nickname.repository.NicknameRepository
import com.freetreechair.domain.signup.taste.repository.TasteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindsNicknameRepository(repository: NicknameRepositoryImpl): NicknameRepository

    @Binds
    @Singleton
    fun bindsDisgustRepository(repository: DisgustRepositoryImpl): DisgustRepository

    @Binds
    @Singleton
    fun bindsTasteRepository(repository: TasteRepositoryImpl): TasteRepository

    @Binds
    @Singleton
    fun bindsEvaluateRepository(repository: EvaluateRepositoryImpl): EvaluateRepository

    @Binds
    @Singleton
    fun bindsCompleteRepository(repository: CompleteRepositoryImpl): CompleteRepository
}
