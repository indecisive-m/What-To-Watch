package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.R
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.presentation.details.DetailsScreenAction

@Composable
fun Recommendations(
    movie: List<Movie>,
    tv: List<Tv>,
    onClick: (DetailsScreenAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val list = movie.ifEmpty { tv }

    val screenSize = LocalWindowInfo.current.containerDpSize

    Column(
        modifier = modifier
            .padding(16.dp)

    ) {
        Text(
            text = stringResource(R.string.recommendations),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineSmall,
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(list) { item ->

                Column(
                    modifier = Modifier
                        .width(screenSize.width * 0.35F)
                        .clickable(
                            onClick = {
                                onClick(
                                    DetailsScreenAction.OnItemClick(
                                        id = item.id,
                                        mediaType = item.mediaType
                                    )
                                )
                            }
                        )

                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.posterPath)
                            .crossfade(enable = true)
                            .build(),
                        contentDescription = null,
                        error = rememberVectorPainter(Icons.Default.Movie),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .aspectRatio(2F / 3f)
                            .height(175.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        placeholder = rememberVectorPainter(Icons.Default.Movie)
                    )
                    Spacer(Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.height(54.dp)
                    ) {
                        Text(
                            text = when (item) {
                                is Movie -> item.title.toString()
                                is Tv -> item.name.toString()
                                else -> ""
                            },
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}
