package com.kroy.multimodule.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kroy.multimodule.data.repository.postRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewmodel @Inject constructor(val postRepository: postRepository):ViewModel() {
    val list = postRepository.getPosts().cachedIn(viewModelScope)
}