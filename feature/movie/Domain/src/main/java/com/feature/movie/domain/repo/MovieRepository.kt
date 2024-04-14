package com.feature.movie.domain.repo

import com.feature.movie.domain.model.Movie

interface MovieRepository {
    /**
     * fitering of a big data into only those data which we need
     */

    suspend fun getMovieList(apiKey:String,q:String):List<Movie>  // here we want only the image Url
}