package com.feature.movie.data.mapper

import android.util.Log
import com.core.network.model.MovieListResponse
import com.feature.movie.domain.model.Movie


    fun MovieListResponse.toDomainMovieList():List<Movie>{
        return  this.results.map {
            Log.d("full path->",makeFullurl(it.poster_path))
            Movie(makeFullurl(it.poster_path),it.id.toString() )
        }
    }

fun makeFullurl(path:String) = "https://image.tmdb.org/t/p/w500/${path}"


