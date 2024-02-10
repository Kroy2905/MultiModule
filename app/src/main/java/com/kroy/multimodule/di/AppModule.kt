package com.kroy.multimodule.di

import com.feature.movie.ui.navigation.MovieApi
import com.kroy.multimodule.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNavigationProvider(movieApi: MovieApi):NavigationProvider{
        return NavigationProvider(movieApi)
    }
}