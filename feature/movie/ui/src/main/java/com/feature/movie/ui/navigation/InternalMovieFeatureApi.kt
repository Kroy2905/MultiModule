package com.feature.movie.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.MovieFeature
import com.core.feature_api.FeatureApi

internal object InternalMovieFeatureApi : FeatureApi {
    override fun registerGraph(navController: NavHostController,
                               navGraphBuilder: NavGraphBuilder)
    {
        navGraphBuilder.navigation(startDestination = MovieFeature.movieScreenRoute ,
            route = MovieFeature.nestedRoute){
            composable(MovieFeature.movieScreenRoute ){
              //  MovieScreen()
            }
        }
    }
}