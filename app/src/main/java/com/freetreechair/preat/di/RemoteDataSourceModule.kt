package com.freetreechair.preat.di

import com.freetreechair.data.signup.disgust.remote.RemoteDisgustDataSource
import com.freetreechair.data.signup.disgust.remote.RemoteDisgustDataSourceImpl
import com.freetreechair.data.signup.evaluate.remote.RemoteEvaluateDataSource
import com.freetreechair.data.signup.evaluate.remote.RemoteEvaluateDataSourceImpl
import com.freetreechair.data.auth.login.remote.RemoteLoginDataSource
import com.freetreechair.data.auth.login.remote.RemoteLoginDataSourceImpl
import com.freetreechair.data.signup.complete.remote.RemoteCompleteDataSource
import com.freetreechair.data.signup.complete.remote.RemoteCompleteDataSourceImpl
import com.freetreechair.data.signup.nickname.remote.RemoteNicknameDataSource
import com.freetreechair.data.signup.nickname.remote.RemoteNicknameDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Binds
    @Singleton
    fun bindsRemoteLoginDataSource(source: RemoteLoginDataSourceImpl): RemoteLoginDataSource

    @Binds
    @Singleton
    fun bindsRemoteNicknameDataSource(source: RemoteNicknameDataSourceImpl): RemoteNicknameDataSource

    @Binds
    @Singleton
    fun bindsRemoteDisgustDataSource(source: RemoteDisgustDataSourceImpl): RemoteDisgustDataSource

    @Binds
    @Singleton
    fun bindsRemoteEvaluateDataSource(source: RemoteEvaluateDataSourceImpl): RemoteEvaluateDataSource

    @Binds
    @Singleton
    fun bindsRemoteCompleteDataSource(source: RemoteCompleteDataSourceImpl): RemoteCompleteDataSource
}
