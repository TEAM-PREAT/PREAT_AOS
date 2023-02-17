package com.freetreechair.preat.di

import com.freetreechair.navigator.MainNavigator
import com.freetreechair.preat.navigator.MainNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class NavigatorModule {
    @Binds
    abstract fun provideMainNavigator(
        navigator: MainNavigatorImpl
    ): MainNavigator
}
