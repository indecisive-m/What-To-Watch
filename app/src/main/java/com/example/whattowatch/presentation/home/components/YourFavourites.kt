package com.example.whattowatch.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.R
import com.example.whattowatch.domain.Media
import com.example.whattowatch.presentation.home.HomeScreenAction

@Composable
fun YourFavourites(
    favourites: List<Media>,
    onItemClick: (HomeScreenAction) -> Unit,
    onSeeMoreButtonClick: (HomeScreenAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val favouritedItems = favourites.take(10)

    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.your_favourites),
                style = MaterialTheme.typography.titleLarge
            )
            SeeMoreButton(
                containerColor = Color.Transparent,
                onClick = { onSeeMoreButtonClick(HomeScreenAction.OnSeeMoreButtonClick) }
            )

        }

        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .height(500.dp),
            content = {

                items(favouritedItems) { favourite ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(favourite.posterPath)
                            .crossfade(enable = true)
                            .build(),
                        contentDescription = null,
                        error = rememberVectorPainter(Icons.Default.Movie),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .aspectRatio(2f / 3f)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(onClick = { onItemClick(HomeScreenAction.OnItemClick(favourite.id)) }),
                        placeholder = rememberVectorPainter(Icons.Default.Movie)
                    )

                }
                item(span = { GridItemSpan(2) }) {
                    SeeMoreButton(
                        containerColor = Color.LightGray,
                        onClick = { onSeeMoreButtonClick(HomeScreenAction.OnSeeMoreButtonClick) }
                    )
                }
            }

        )
    }

}


