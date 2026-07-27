package com.example.whattowatch.presentation.home.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.presentation.details.isDateValid
import com.example.whattowatch.presentation.home.HomeScreenAction

@Composable
fun Popular(
    onClick: (HomeScreenAction) -> Unit,
    popular: List<Media>,
    modifier: Modifier = Modifier
) {

    val screenSize = LocalWindowInfo.current.containerDpSize

    Column(
        modifier = modifier
            .padding(16.dp)

    ) {
        Text(
            text = when (popular[0]) {
                is Movie -> "Popular Movies"
                is Tv -> "Popular TV Shows"
                else -> ""
            },
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,

            )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            items(popular) { item ->


                Column(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                onClick(
                                    HomeScreenAction.OnItemClick(
                                        item.id,
                                        item.mediaType
                                    )
                                )
                            }
                        )
                        .width(screenSize.width * 0.35F)

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
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        placeholder = rememberVectorPainter(Icons.Default.Movie)
                    )
                    Spacer(Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.height(72.dp)
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
                        Text(
                            text = when (item) {
                                is Movie -> isDateValid(item.releaseDate).toString()
                                is Tv -> isDateValid(item.firstAirDate).toString()
                                else -> ""
                            },
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium,

                            )

                    }
                }
            }
        }
    }
}
