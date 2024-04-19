package com.kroy.multimodule.data.api


import com.kroy.multimodule.data.models.postResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface APIservice {
    // GET trip details
    @GET("/posts")
    suspend fun getPosts(@Query("_page") page:Int,
                               @Query("_limit") limit:Int
                               ) : Response <postResponse>
}
