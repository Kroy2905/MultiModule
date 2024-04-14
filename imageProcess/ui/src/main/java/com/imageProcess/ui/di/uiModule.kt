package com.imageProcess.ui.di

import com.imageProcess.ui.ImageApi
import com.imageProcess.ui.ImageApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object uiModule {
    @Provides
    fun provideImageImpl():ImageApi{
        return ImageApiImpl()

    }

}