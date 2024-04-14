package com.imageProcess.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

interface ImageApi:FeatureApi {   // interface to register graph

}
class ImageApiImpl:ImageApi{
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalImageFeatureApi.registerGraph(
            navController,navGraphBuilder
        )
    }

}