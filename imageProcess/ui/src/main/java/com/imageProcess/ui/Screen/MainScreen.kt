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
import androidx.compose.foundation.layout.size
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



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {
    val result by viewModel.imageList
    var page by remember { mutableStateOf(1) }
    val gridState = rememberLazyGridState()
    val allImages = remember { mutableStateListOf<imageItem>() }

    LaunchedEffect(result) {
        if (result != null && !result.isLoading && result.error.isBlank() && result.data != null) {
            allImages.addAll(result.data!!)
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
                    page++
                    viewModel.getImageList(page)
                }
            }
        }

        when {
            result?.isLoading == true && allImages.isEmpty() -> LoadingIndicator()
            result?.error?.isNotBlank() == true && allImages.isEmpty() -> ErrorText(result.error)
            allImages.isEmpty() -> EmptyStateText("No Images to show")
        }
    }
}

@Composable
private fun ImageItem(imageData: imageItem) {
    val bitmapState = remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(imageData.imageUrl) {
        val bitmap = loadImage(imageData.imageUrl)
        bitmapState.value = bitmap
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .border(1.dp, color = Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        bitmapState.value?.let { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize() // Fill the entire Box
            )
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

private suspend fun loadImage(url: String): ImageBitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val bitmap = imageCache[url] ?: run {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream = connection.inputStream
                val decodedBitmap = BitmapFactory.decodeStream(inputStream)?.asImageBitmap()
                inputStream.close()
                connection.disconnect()
                decodedBitmap?.also { imageCache[url] = it }
            }
            bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}

val imageCache = mutableMapOf<String, ImageBitmap?>()
