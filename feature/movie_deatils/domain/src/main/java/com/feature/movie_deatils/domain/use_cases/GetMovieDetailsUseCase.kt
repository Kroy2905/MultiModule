package com.feature.movie_deatils.domain.use_cases

import com.core.common.UiEvent
import com.feature.movie_deatils.domain.model.MovieDetails
import com.feature.movie_deatils.domain.repository.MovieDetailsrepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private  val movieDetailsrepo: MovieDetailsrepo){

    operator  fun invoke(id:String,apiKey:String) = flow {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(movieDetailsrepo.getMovieDetails(id,apiKey)))

    }.catch {
        emit(UiEvent.Failure(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}