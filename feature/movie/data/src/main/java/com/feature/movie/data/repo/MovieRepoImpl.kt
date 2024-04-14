package com.feature.movie.data.repo

import com.core.network.dataproviders.NetworkDataProviders
import com.feature.movie.data.mapper.toDomainMovieList
import com.feature.movie.domain.model.Movie
import com.feature.movie.domain.repo.MovieRepository
import javax.inject.Inject



class MovieRepoImpl @Inject constructor(private  val networkDataProviders: NetworkDataProviders) :
    MovieRepository {
        override suspend fun getMovieList(apiKey: String,q:String): List<Movie> {
        return  networkDataProviders.getMovieList(apikey = apiKey, q = q).toDomainMovieList()
    }



}