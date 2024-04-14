package com.imageProcess.domain.di

import com.imageProcess.domain.repo.imgeRepository
import com.imageProcess.domain.use_cases.GetImageListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetImageListUseCase(imgeRepository: imgeRepository):GetImageListUseCase{
        return GetImageListUseCase(imgeRepository)
    }

}