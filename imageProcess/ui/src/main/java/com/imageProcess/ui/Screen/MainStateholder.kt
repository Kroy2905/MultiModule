package com.imageProcess.ui.Screen

import com.imageProcess.domain.model.imageItem

class MainStateholder (
    val isLoading : Boolean = false,
    val data : List<imageItem>?=null,
    val error :String = ""
)


