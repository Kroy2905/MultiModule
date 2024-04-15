package com.kroy.multimodule.di

import com.imageProcess.ui.ImageApi
import com.kroy.multimodule.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    fun provideNavigationProvider(imageApi: ImageApi):NavigationProvider{
        return NavigationProvider(imageApi)
    }
}