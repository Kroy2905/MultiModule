package com.imageProcess.data.di

import com.core.network.dataproviders.NetworkDataProviders
import com.imageProcess.data.repo.ImageRepoImpl
import com.imageProcess.domain.repo.imgeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    fun provideImageRepo(networkDataProviders: NetworkDataProviders):imgeRepository{
        return ImageRepoImpl(networkDataProviders = networkDataProviders)
    }
}