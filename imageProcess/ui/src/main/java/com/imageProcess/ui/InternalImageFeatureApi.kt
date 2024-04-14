package com.imageProcess.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.ImageProcess
import com.core.feature_api.FeatureApi
import com.imageProcess.ui.Screen.MainScreen
import com.imageProcess.ui.Screen.MainViewModel

internal  object InternalImageFeatureApi:FeatureApi {
    override fun registerGraph(navController: NavHostController,
                               navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(startDestination = ImageProcess.screenRoute,
            route = ImageProcess.nestedRoute){
            composable(ImageProcess.screenRoute){
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(viewModel,navController)
            }
        }

    }
}