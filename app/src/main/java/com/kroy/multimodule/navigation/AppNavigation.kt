package com.kroy.multimodule.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constants.ImageProcess
import com.core.common.navigation_constants.MovieFeature

@Composable
fun AppNavGraph(
    navController : NavHostController ,
    navigationProvider: NavigationProvider
        ){
    NavHost(navController = navController, startDestination = ImageProcess.nestedRoute ){
//        navigationProvider.movieApi.registerGraph(
//            navController,this
//        )
//        navigationProvider.movieDetailsApi.registerGraph(
//            navController,this
//        )
        navigationProvider.imageApi.registerGraph(
            navController,this
        )
    }

}