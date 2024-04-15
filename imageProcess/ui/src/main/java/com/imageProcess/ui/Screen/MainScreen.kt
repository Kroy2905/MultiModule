package com.imageProcess.ui.Screen

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.imageProcess.domain.model.imageItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {
    val result = viewModel.imageList.value
    var page by remember { mutableStateOf(1) }
    val gridState = rememberLazyGridState()
    val allImages = remember { mutableStateListOf<imageItem>() } // stores the downloaded data
    /**
     * LanuchedEffect is used to launch the block in the Coroutine Context of the composoble
     * its used for asynchronous functionality
     * it obseves the result
     */
    LaunchedEffect(result) {
        if (result != null && !result.isLoading && result.error.isBlank() && result.data != null) {
            allImages.addAll(result.data)
        }
    }

    Scaffold {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = gridState
        ) {
            items(allImages.size) { index ->
                val item = allImages[index]
                ImageItem(item)
                if (index == allImages.lastIndex && !result.isLoading && result.error.isBlank()) {
                    // Load more images when reaching the bottom
                    page++
                    viewModel.getImageList(page)
                }
            }
        }

        if (result?.isLoading == true && allImages.isEmpty()) {
            LoadingIndicator()
        } else if (result?.error?.isNotBlank() == true && allImages.isEmpty()) {
            ErrorText(result.error)
        } else if (allImages.isEmpty()) {
            EmptyStateText("No Images to show")
        }
    }
}

    @Composable
    private fun LoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun ErrorText(error: String) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = error)
        }
    }

    @Composable
    private fun EmptyStateText(message: String) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = message)
        }
    }


@Composable
private fun ImageItem(imageData: imageItem) {
    var bitmapState by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(imageData.imageUrl) {
        try {
            val bitmap = loadImage(imageData.imageUrl)
            bitmapState = bitmap
        } catch (e: IOException) {
            // Handle the error, e.g., show a placeholder image
        }
    }

    Box(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .border(1.dp, color = Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        val bitmap = bitmapState
        bitmap?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize() // Fill the entire Box
            )
        }
    }
}


private suspend fun loadImage(url: String): ImageBitmap? {
    return withContext(Dispatchers.IO) {
        try {

            val connection = URL(url).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val inputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            connection.disconnect()
            bitmap?.asImageBitmap()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}


