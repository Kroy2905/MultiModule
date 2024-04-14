package com.feature.movie_details.data.di

import com.core.network.dataproviders.NetworkDataProviders
import com.feature.movie_deatils.domain.repository.MovieDetailsrepo
import com.feature.movie_details.data.repo.MovieDetailsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object DataModule {



    @Provides
    fun provideMovieDetailsRepo(networkDataProviders: NetworkDataProviders):MovieDetailsrepo{
        return MovieDetailsRepoImpl(networkDataProviders)
    }
}