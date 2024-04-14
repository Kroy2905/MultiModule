package com.feature.movie_details.data.repo

import android.util.Log
import com.core.network.dataproviders.NetworkDataProviders
import com.feature.movie_deatils.domain.model.MovieDetails
import com.feature.movie_deatils.domain.repository.MovieDetailsrepo
import com.feature.movie_details.data.mappers.toDomain
import javax.inject.Inject

class MovieDetailsRepoImpl @Inject constructor(private  val networkDataProviders: NetworkDataProviders): MovieDetailsrepo {
    override suspend fun getMovieDetails(id: String, apikey: String): MovieDetails {
        Log.d("MovieDetails->", networkDataProviders.getMovieDetails(id,apikey).toDomain().toString())
        return  networkDataProviders.getMovieDetails(id,apikey).toDomain()
    }
}