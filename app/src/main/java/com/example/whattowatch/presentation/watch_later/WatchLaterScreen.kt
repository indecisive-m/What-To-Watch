package com.example.whattowatch.presentation.watch_later

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.koin.androidx.compose.koinViewModel

@Composable
fun WatchLaterScreenRoot(
    viewModel: WatchLaterViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    WatchLaterScreen(
        state = state.value,
        modifier = Modifier
    )
}


@Composable
fun WatchLaterScreen(
    state: WatchLaterState,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .height(500.dp),
            content = {

                items(state.watchLaterItems) { item ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.posterPath)
                            .crossfade(enable = true)
                            .build(),
                        contentDescription = null,
                        error = rememberVectorPainter(Icons.Default.Movie),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .aspectRatio(2f / 3f)
                            .clip(RoundedCornerShape(8.dp)),
                        placeholder = rememberVectorPainter(Icons.Default.Movie)
                    )

                }

            }

        )
    }
}






