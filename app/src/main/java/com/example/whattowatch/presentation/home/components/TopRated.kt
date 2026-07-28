package com.example.whattowatch.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.R
import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.presentation.details.isDateValid
import com.example.whattowatch.presentation.home.HomeScreenAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopRated(
    topRated: List<Media>,
    onClick: (HomeScreenAction) -> Unit,
    modifier: Modifier = Modifier


) {

    val screenSize = LocalWindowInfo.current.containerDpSize

    val state = rememberPagerState { topRated.size }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = when (topRated[0]) {
                is Movie -> stringResource(R.string.top_rated_movies)
                is Tv -> stringResource(R.string.top_rated_tv_shows)
                else -> ""
            },
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,

            )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(Modifier.height(16.dp))

        HorizontalPager(
            state = state,
            pageSpacing = 16.dp,
            snapPosition = SnapPosition.Center,
            pageSize = PageSize.Fixed(screenSize.width * 0.6F),

            ) { i ->


            val item = topRated[i]

            Box(
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
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.backdropPath)
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = null,
                    error = rememberVectorPainter(Icons.Default.Movie),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .aspectRatio(16f / 9f)
                        .clip(RoundedCornerShape(8.dp))
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = size.height * 0.3f,
                                endY = size.height


                            )
                            onDrawWithContent {
                                drawContent()
                                drawRoundRect(
                                    brush = gradient,
                                    cornerRadius = CornerRadius(8.dp.toPx())

                                )

                            }
                        },

                    placeholder = rememberVectorPainter(Icons.Default.Movie)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = when (item) {
                            is Movie -> item.title.toString()
                            is Tv -> item.name.toString()
                            else -> ""
                        },
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.titleLarge

                    )
                    Text(
                        text = when (item) {
                            is Movie -> isDateValid(item.releaseDate).toString()
                            is Tv -> isDateValid(item.firstAirDate).toString()
                            else -> ""
                        },
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }


        }

    }

}
