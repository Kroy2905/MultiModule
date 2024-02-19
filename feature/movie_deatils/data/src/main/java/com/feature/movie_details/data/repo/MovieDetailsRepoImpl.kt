package com.feature.movie_details.data.repo

import com.core.network.dataproviders.MovieDataProviders
import com.feature.movie_deatils.domain.model.MovieDetails
import com.feature.movie_deatils.domain.repository.MovieDetailsrepo
import com.feature.movie_details.data.mappers.toDomain
import javax.inject.Inject

class MovieDetailsRepoImpl @Inject constructor(private  val movieDataProviders: MovieDataProviders): MovieDetailsrepo {
    override suspend fun getMovieDetails(id: String, apikey: String): MovieDetails {
        return  movieDataProviders.getMovieDetails(id,apikey).toDomain()
    }
}