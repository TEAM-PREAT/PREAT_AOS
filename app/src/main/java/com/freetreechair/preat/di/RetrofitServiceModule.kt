package com.freetreechair.preat.di

import com.freetreechair.data.signup.disgust.remote.service.DisgustService
import com.freetreechair.data.signup.evaluate.remote.service.EvaluateService
import com.freetreechair.data.auth.login.remote.service.LoginService
import com.freetreechair.data.signup.complete.remote.service.CompleteService
import com.freetreechair.data.signup.nickname.remote.service.NicknameService
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

    @Provides
    @Singleton
    fun providesNicknameService(@PreatServer retrofit: Retrofit): NicknameService =
        retrofit.create(NicknameService::class.java)

    @Provides
    @Singleton
    fun providesDisgustService(@PreatServer retrofit: Retrofit): DisgustService =
        retrofit.create(DisgustService::class.java)

    @Provides
    @Singleton
    fun providesEvaluateService(@PreatServer retrofit: Retrofit): EvaluateService =
        retrofit.create(EvaluateService::class.java)

    @Provides
    @Singleton
    fun providesCompleteService(@PreatServer retrofit: Retrofit): CompleteService =
        retrofit.create(CompleteService::class.java)
}
