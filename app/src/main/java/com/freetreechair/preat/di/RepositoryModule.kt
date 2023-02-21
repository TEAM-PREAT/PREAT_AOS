package com.freetreechair.preat.di

import com.freetreechair.data.login.repository.LoginRepositoryImpl
import com.freetreechair.data.nickname.repository.NicknameRepositoryImpl
import com.freetreechair.domain.login.repository.LoginRepository
import com.freetreechair.domain.nickname.repository.NicknameRepository
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
}
