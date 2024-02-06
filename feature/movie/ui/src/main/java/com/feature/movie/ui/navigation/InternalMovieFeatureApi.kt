package com.feature.movie.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.feature_api.FeatureApi

object InternalMovieFeatureApi : FeatureApi {
    override fun registerGraph(navController: NavHostController,
                               navGraphBuilder: NavGraphBuilder)
    {
        navGraphBuilder.navigation(startDestination = "movie" ,
            route = "movie_nested_navigation"){
            composable("movie"){
              //  MovieScreen()
            }
        }
    }
}