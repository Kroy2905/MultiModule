package com.feature.movie.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.ImageProcess
import com.core.common.navigation_constants.MovieFeature
import com.core.feature_api.FeatureApi
import com.feature.movie.ui.navigation.Screen.MovieScreen
import com.feature.movie.ui.navigation.Screen.MovieSearchViewModel

internal object InternalMovieFeatureApi : FeatureApi {
    override fun registerGraph(navController: NavHostController,
                               navGraphBuilder: NavGraphBuilder)
    {
        navGraphBuilder.navigation(startDestination = ImageProcess.screenRoute ,
            route = ImageProcess.nestedRoute){
            composable(ImageProcess.screenRoute ){
              //  MovieScreen()
                val viewModel = hiltViewModel<MovieSearchViewModel>()
                MovieScreen(viewModel,navController)
            }
        }
    }
}