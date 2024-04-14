package com.imageProcess.data.mapper

import android.util.Log

import com.core.network.model.imageListResponse
import com.imageProcess.domain.model.imageItem

fun imageListResponse.toDomainImageList():List<imageItem>{

    return  this.map {
       imageItem(id = it.id,
           imageUrl =  it.urls.regular)
    }
}