package com.core.network

import com.core.network.model.MovieDetailsDTO
import com.core.network.model.MovieListResponse
import com.core.network.model.imageListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
        //https://api.themoviedb.org/3/search/movie?api_key=9011c63707a23c0975e5af8af126617b&query=Harry
        @GET("3/search/movie")
        suspend fun getMovieList(
            @Query("api_key") apiKey:String,
            @Query("query") q:String
        ):MovieListResponse


    @GET("/photos")
    suspend fun getImageList(
        @Query("client_id") clientId:String,
        @Query("page") page:Int,
        @Query("per_page") perPage:Int  // total items return
    ):imageListResponse



    @GET("3/movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id:String,
        @Query("api_key") apiKey:String
    ):MovieDetailsDTO
}