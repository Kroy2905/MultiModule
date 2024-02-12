package com.feature.movie.ui.navigation.Screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.feature.movie.domain.use_cases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(FlowPreview::class)
@HiltViewModel
class MovieSearchViewModel @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase)
    : ViewModel() {
    val API_KEY ="9011c63707a23c0975e5af8af126617b"

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query:StateFlow<String> get() = _query

   private  val _movieList = mutableStateOf(MovieSearchStateHolder())
    val movieList: State<MovieSearchStateHolder> get() = _movieList


    init {
        viewModelScope.launch {
            query
                .debounce(1000)
                .collectLatest { qr ->
                    getMovieList(qr)
            }

        }


    }
    fun getMovieList( q:String)= viewModelScope.launch {
        getMovieListUseCase(apiKey = API_KEY, q = q).onEach {
            when(it){
                is UiEvent.Loading->{
                    _movieList.value = MovieSearchStateHolder(isLoading = true)
                }
                is UiEvent.Success->{
                    _movieList.value = MovieSearchStateHolder(data = it.data)
                }
                is UiEvent.Failure->{
                    _movieList.value = MovieSearchStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }


    fun setQuery(s:String){
        _query.value = s
//        getMovieList( s)
    }


}