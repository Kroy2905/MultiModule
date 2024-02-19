package com.feature.movie_details.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.MovieDetailsFeature
import com.core.feature_api.FeatureApi
import com.feature.movie_details.ui.screen.MovieDetailsScreen
import com.feature.movie_details.ui.screen.MovieDetailsViewModel

object InternalMovideDetailsApi : FeatureApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = MovieDetailsFeature.nestedRoute,
            startDestination = MovieDetailsFeature.movieScreenRoute
            ){
            composable(MovieDetailsFeature.movieScreenRoute){
                val id = it.arguments?.getString("id")
                val viewmodel = hiltViewModel<MovieDetailsViewModel>()
                MovieDetailsScreen(id = id.toString(), viewModel = viewmodel)
            }
        }
    }
}