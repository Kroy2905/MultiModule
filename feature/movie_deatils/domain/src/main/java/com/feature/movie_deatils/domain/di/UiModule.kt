package com.feature.movie_deatils.domain.di

import com.feature.movie_deatils.domain.repository.MovieDetailsrepo
import com.feature.movie_deatils.domain.use_cases.GetMovieDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideGetMovieDetailUseCase(movieDetailsrepo: MovieDetailsrepo): GetMovieDetailsUseCase{
        return GetMovieDetailsUseCase((movieDetailsrepo))
    }

}