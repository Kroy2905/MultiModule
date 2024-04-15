package com.kroy.multimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kroy.multimodule.navigation.AppNavGraph
import com.kroy.multimodule.navigation.NavigationProvider
import com.kroy.multimodule.ui.theme.MultiModuleAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var  navigationProvider: NavigationProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MultiModuleAppTheme {
                val navController = rememberNavController()
                App(navHostController = navController,navigationProvider)
            }
        }
    }
}
@Composable
fun App(navHostController: NavHostController, navigationProvider: NavigationProvider){
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
       AppNavGraph(navController = navHostController,
           navigationProvider = navigationProvider)
    }
}
