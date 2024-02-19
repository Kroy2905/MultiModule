package com.feature.movie_details.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.feature.movie_deatils.domain.use_cases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private  val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle)
    :ViewModel(){

    val API_KEY ="9011c63707a23c0975e5af8af126617b"

    private  val _movieDetails = mutableStateOf(MovieDetailsStateHolder())
    val movieDetails: State<MovieDetailsStateHolder> get() = _movieDetails

    init{
        savedStateHandle.getLiveData<String>("id").observeForever{
            it?.let{
                getMovieDetails(it,API_KEY)
            }
        }
    }

    fun getMovieDetails(id:String,apiKey:String){
        getMovieDetailsUseCase(id,apiKey).onEach {
            when(it){
                is UiEvent.Loading->{
                    _movieDetails.value = MovieDetailsStateHolder(isLoading = true)
                }
                is UiEvent.Success->{
                    _movieDetails.value = MovieDetailsStateHolder(data = it.data)
                }
                is UiEvent.Failure->{
                    _movieDetails.value = MovieDetailsStateHolder(error = it.message.toString())
                }
            }


        }.launchIn(viewModelScope)
    }



}