package com.example.whattowatch.presentation.search_results.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.presentation.search_results.Status

@Composable
fun UpComingMovies(
    item: List<Movie?>,
    loadUpComingMovies: () -> Unit,
    status: Status,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(key1 = Unit) {
        loadUpComingMovies()
    }


    val fallbackImage = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"

    when (status) {
        Status.LOADING, Status.IDLE -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator() // Or a skeleton loading screen
            }
        }

        Status.SUCCESS -> {
            var currentUrl by remember { mutableStateOf(item[0]?.backdropPath) }



            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(currentUrl)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                onError = {
                    if (currentUrl != fallbackImage) {
                        currentUrl = fallbackImage
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(400.dp)
                    .background(Color.Red),
            )
        }

        Status.ERROR -> {
            Text("error")
        }
    }


}

