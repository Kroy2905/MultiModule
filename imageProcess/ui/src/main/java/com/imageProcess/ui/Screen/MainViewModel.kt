package com.imageProcess.ui.Screen

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.imageProcess.domain.use_cases.GetImageListUseCase
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
class MainViewModel  @Inject constructor(private val  getImageListUseCase: GetImageListUseCase):ViewModel(){
    val CLIENT_ID ="RIzBEhxABYFY5tm8H3n_X5Uh0NwerWDBHkx0Hk43g9Q"

    private val _page : MutableStateFlow<Int> = MutableStateFlow(1)
    val page:StateFlow<Int> get() = _page
    private val _imageList = mutableStateOf(MainStateholder())
    val imageList: State<MainStateholder> get() = _imageList
    init {
        viewModelScope.launch {
            page
                .debounce(1000)
                .collectLatest { page ->
                    getImageList(page)
                }

        }


    }


    fun getImageList(page:Int) = viewModelScope.launch {
        getImageListUseCase(
            clientId = CLIENT_ID,
            page = page,
            perPage = 30  // per page return 30
        ).onEach {
            when(it){
                is UiEvent.Loading->{
                    _imageList.value = MainStateholder(isLoading = true)
                }
                is UiEvent.Success->{
                    _imageList.value = MainStateholder(data = it.data)
                }
                is UiEvent.Failure->{
                    _imageList.value = MainStateholder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setpage(page: Int){
        _page.value = page
    }


}