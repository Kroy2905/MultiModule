package com.core.network.daggerHilt

import com.core.network.ApiService
import com.core.network.dataproviders.MovieDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
  @InstallIn(SingletonComponent::class): Annotation used to specify that the bindings in this module should be installed in the SingletonComponent.
 *     This means that the provided dependencies will have a singleton scope, ensuring that there's only one instance of ApiService throughout the application's lifecycle.

@Module: Annotation indicating that this class is a Dagger module. Modules are responsible for providing dependencies.

@Provides: Annotation used to mark methods inside a Dagger module that provide instances of dependencies.

provideApiService(): This method is responsible for creating and providing an instance of ApiService.
  It uses Retrofit to create a Retrofit instance with a base URL set to "https://api.themoviedb.org/", adds Gson converter factory for JSON serialization/deserialization, and then creates an instance of ApiService using the Retrofit instance.

Overall, this module sets up the networking dependencies required for accessing the Movie Database API (assuming that's what ApiService is intended for) using Dagger Hilt. Remember to include this module in your Dagger Hilt setup, typically in your @HiltAndroidApp class or wherever your Hilt setup is centralized.
 */


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {


    @Provides
    fun provideApiService():ApiService{
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

    @Provides
    fun provideMovieDataProvider(apiService: ApiService):MovieDataProviders{
        return MovieDataProviders(apiService)
    }
}