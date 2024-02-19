package com.feature.movie_deatils.domain.repository

import com.feature.movie_deatils.domain.model.MovieDetails

interface MovieDetailsrepo {
    suspend fun getMovieDetails(id:String,apikey:String) : MovieDetails
}