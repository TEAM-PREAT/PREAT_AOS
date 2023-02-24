package com.freetreechair.preat.di

import com.freetreechair.data.disgust.repository.DisgustRepositoryImpl
import com.freetreechair.data.evaluate.repository.EvaluateRepositoryImpl
import com.freetreechair.data.login.repository.LoginRepositoryImpl
import com.freetreechair.data.nickname.repository.NicknameRepositoryImpl
import com.freetreechair.data.taste.repository.TasteRepositoryImpl
import com.freetreechair.domain.disgust.repository.DisgustRepository
import com.freetreechair.domain.evaluate.repository.EvaluateRepository
import com.freetreechair.domain.login.repository.LoginRepository
import com.freetreechair.domain.nickname.repository.NicknameRepository
import com.freetreechair.domain.taste.repository.TasteRepository
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
}
