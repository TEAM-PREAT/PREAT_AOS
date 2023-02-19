package com.freetreechair.preat.di

import com.freetreechair.data.login.remote.service.LoginService
import com.freetreechair.preat.annotations.PreatServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    @Singleton
    fun providesLoginService(@PreatServer retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)
}
