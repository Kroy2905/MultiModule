package com.feature.movie.ui.navigation.Screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.feature.movie.domain.use_cases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieSearchViewModel @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase)
    : ViewModel() {

    val API_KEY ="9011c63707a23c0975e5af8af126617b"

    init {
      viewModelScope.launch {
          _query.debounce(1000)  // wait for 1 sec to type then hit api to avoid too many api calls
              .collectLatest {
                  getMovieList(API_KEY,it)
              }
      }
    }

   private  val _movieList = mutableStateOf(MovieSearchStateHolder())
    val movieList: State<MovieSearchStateHolder> get() = _movieList

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query:StateFlow<String> get() = _query

    fun getMovieList(apikey:String , q:String)= viewModelScope.launch {
        getMovieListUseCase(apiKey = apikey, q = q).onEach {
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
        _query.value=s
    }


}