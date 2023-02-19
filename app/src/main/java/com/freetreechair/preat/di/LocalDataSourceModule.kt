package com.freetreechair.preat.di

import com.freetreechair.data.preferences.PreferencesDataSource
import com.freetreechair.data.preferences.PreferencesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {
    @Binds
    @Singleton
    fun bindsPreferencesDataSource(source: PreferencesDataSourceImpl): PreferencesDataSource
}
