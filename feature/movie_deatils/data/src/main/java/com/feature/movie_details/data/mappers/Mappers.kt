package com.feature.movie_details.data.mappers

import com.core.network.model.MovieDetailsDTO
import com.feature.movie_deatils.domain.model.MovieDetails

fun MovieDetailsDTO.toDomain(): MovieDetails {
        return MovieDetails(
            title =  this.original_title,
            desc =  this.overview,
            imageUrl = makeFullurl(this.poster_path)

        )
}

fun makeFullurl(path:String) = "https://image.tmdb.org/t/p/w500/${path}"