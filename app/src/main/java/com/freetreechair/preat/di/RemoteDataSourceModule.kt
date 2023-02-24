package com.freetreechair.preat.di

import com.freetreechair.data.disgust.remote.RemoteDisgustDataSource
import com.freetreechair.data.disgust.remote.RemoteDisgustDataSourceImpl
import com.freetreechair.data.evaluate.remote.RemoteEvaluateDataSource
import com.freetreechair.data.evaluate.remote.RemoteEvaluateDataSourceImpl
import com.freetreechair.data.login.remote.RemoteLoginDataSource
import com.freetreechair.data.login.remote.RemoteLoginDataSourceImpl
import com.freetreechair.data.nickname.remote.RemoteNicknameDataSource
import com.freetreechair.data.nickname.remote.RemoteNicknameDataSourceImpl
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
}
