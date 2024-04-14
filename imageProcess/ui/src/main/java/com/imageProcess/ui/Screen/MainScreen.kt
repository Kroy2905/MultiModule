package com.imageProcess.ui.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen (viewModel: MainViewModel,navController: NavController){
    val result = viewModel.imageList.value
    var page by remember {
        mutableIntStateOf(1)
    }

    Scaffold {
        if(result.isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if(result.error.isNotBlank()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = result.error)
            }
        }

        result.data?.let {
            if(it.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Nothing Found")
                }
            }else{
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(128.dp),
                    content = {
                        items(it){
                            Box(modifier = Modifier
                                .height(200.dp)
                                .border(1.dp, color = Color.White)

                            ){
                                AsyncImage(
//                                    modifier = Modifier.clickable {
//                                        navController.navigate("movie_details/${it.id}")
//                                    },
                                    model = it.imageUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                )
            }
        }
    }


}