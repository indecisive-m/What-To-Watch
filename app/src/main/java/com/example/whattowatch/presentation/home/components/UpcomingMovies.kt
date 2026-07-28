package com.example.whattowatch.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.R
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.presentation.home.HomeScreenAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpComingMovies(
    items: List<Movie>,
    onItemClick: (HomeScreenAction) -> Unit,
    modifier: Modifier = Modifier
) {
//
//    val screenConfiguration = LocalConfiguration.current
//
//    val isLandscape = screenConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val fallbackImage =
        "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"


    val pagerState = rememberPagerState(pageCount = { 8 })



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.new_upcoming),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,

            )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.outline
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .height(450.dp)
                    .aspectRatio(2F / 3F, matchHeightConstraintsFirst = true)
                    .clip(RoundedCornerShape(16.dp)),
                pageSpacing = 24.dp,


                ) { page ->

                val upcomingMovie = items[page]

                var currentUrl = upcomingMovie?.posterPath ?: fallbackImage


                Box(
                    modifier = Modifier.clickable(onClick = {
                        onItemClick(
                            HomeScreenAction.OnItemClick(
                                upcomingMovie!!.id,
                                mediaType = upcomingMovie.mediaType
                            )
                        )
                    })
                ) {
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
                        modifier = Modifier.fillMaxWidth()
                    )
                    Column(
                        Modifier
                            .align(Alignment.BottomStart)
                            .padding(
                                vertical = 24.dp,
                                horizontal = 16.dp
                            )
                    ) {
                        Text(
                            text = upcomingMovie?.title.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSecondary,

                            )
                        Text(
                            text = upcomingMovie?.averageVote.toString(),
                            color = MaterialTheme.colorScheme.onSecondary,

                            )

                    }


                }


            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                repeat(pagerState.pageCount) { iteration ->

                    val HEIGHT = 8.dp
                    val WIDTH = HEIGHT * 4

                    val color =
                        if (pagerState.currentPage == iteration) Color.White else Color.LightGray

                    val shape =
                        if (pagerState.currentPage == iteration) RoundedCornerShape(10.dp) else CircleShape
                    val size = if (pagerState.currentPage == iteration) WIDTH else HEIGHT

                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(shape)
                            .background(MaterialTheme.colorScheme.primary)
                            .width(size)
                            .height(HEIGHT)
                    )


                }
            }

        }

    }
}





