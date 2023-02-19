package com.freetreechair.preat.di

import com.freetreechair.data.login.remote.RemoteLoginDataSource
import com.freetreechair.data.login.remote.RemoteLoginDataSourceImpl
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
}
