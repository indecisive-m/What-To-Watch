package com.example.whattowatch.presentation.search_results.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.Movie

@Composable
fun UpComingMovies(
    item: Media,
    modifier: Modifier = Modifier
) {

    when (item) {
        is Movie -> {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.backdropPath)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = null,
                error = rememberVectorPainter(Icons.Default.Movie),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(150.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = rememberVectorPainter(Icons.Default.Movie)
            )

        }

        else -> Unit

    }

}