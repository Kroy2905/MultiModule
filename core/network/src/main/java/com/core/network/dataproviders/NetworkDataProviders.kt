package com.core.network.dataproviders

import com.core.network.ApiService
import javax.inject.Inject

class NetworkDataProviders @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList(apikey:String,q:String) = apiService.getMovieList(apikey,q)
    suspend fun getMovieDetails(id:String,apikey:String) = apiService.getMovieDetails(id = id, apiKey = apikey)
    suspend fun getImageList(clientId:String,page:Int,perPage:Int) = apiService.getImageList(
        clientId = clientId,
        page = page,
        perPage = perPage
    )

}