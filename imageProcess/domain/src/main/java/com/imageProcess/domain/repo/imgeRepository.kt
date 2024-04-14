package com.imageProcess.domain.repo

import com.imageProcess.domain.model.imageItem

interface imgeRepository {
    suspend fun getImageList(
        clientId:String,
        page:Int,
        perPage:Int
    ):List<imageItem>  // here we want only the image Url
}