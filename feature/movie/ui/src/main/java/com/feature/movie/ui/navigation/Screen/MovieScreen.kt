package com.feature.movie.ui.navigation.Screen
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(viewModel: MovieSearchViewModel){
    val result = viewModel.movieList.value
    var query by remember {
        mutableStateOf("")
    }


    Scaffold (topBar = {
        TextField(
            modifier= Modifier.fillMaxWidth(),
            value = query,
            onValueChange = {
                Log.d("full path->",it)
                query = it
            viewModel.setQuery(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ), placeholder = {
                Text(text = "Search Movie ...")
            }, trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            })
    } ){
        Log.d("TAG","MovieScreen : ${it}")
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