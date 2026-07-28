package com.example.whattowatch.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.R
import com.example.whattowatch.domain.MovieDetails
import com.example.whattowatch.domain.TvDetails
import com.example.whattowatch.presentation.details.components.GenreChip
import com.example.whattowatch.presentation.details.components.IconRow
import com.example.whattowatch.presentation.details.components.InfoRow
import com.example.whattowatch.presentation.details.components.InfoTabRow
import com.example.whattowatch.presentation.details.components.ReviewComposable
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DetailsScreenRoot(
    viewModel: DetailsScreenViewModel = koinViewModel<DetailsScreenViewModel>(),
    onBackClick: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    DetailsScreen(
        state = state.value,
        onBackClick = onBackClick,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun DetailsScreen(
    state: DetailsScreenState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onAction: (DetailsScreenAction) -> Unit
) {

    var isImageLoading by remember { mutableStateOf(true) }

    val media = state.media


    when (state.status) {
        Status.IDLE, Status.LOADING -> {
            LoadingSpinner()
        }

        Status.SUCCESS -> {

            if (media == null) {
                return

            }

            val fallbackImage =
                "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            var currentUrl by remember(media.posterPath) {
                mutableStateOf(media.posterPath ?: fallbackImage)
            }

            val validDate = when (media) {
                is MovieDetails -> isDateValid(media.releaseDate)
                is TvDetails -> isDateValid(media.firstAirDate)
                else -> null
            }


            val productionCompaniesWithLogos = remember {
                when (media) {
                    is MovieDetails -> media.productionCompanies.filter { it.logoPath != null }
                    is TvDetails -> media.productionCompanies.filter { it.logoPath != null }
                    else -> null
                }
            }

            val castWithProfilePicture = remember {
                when (media) {
                    is MovieDetails -> media.credits.cast.filter { it.profilePath != null }
                    else -> null
                }
            }
            val tvCastWithProfilePicture = remember {
                when (media) {
                    is TvDetails -> media.credits.tvCast.filter { it.profilePath != null }
                    else -> null
                }
            }

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)

            ) {


                LazyColumn(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom)),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    item {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(currentUrl)
                                .crossfade(enable = true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            onSuccess = {
                                isImageLoading = false
                            },
                            onError = {
                                if (currentUrl != fallbackImage) {
                                    currentUrl = fallbackImage
                                }
                                isImageLoading = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(16.dp))

                    }

                    item {

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .align(Alignment.Center)
                        ) {
                            Text(
                                text = when (media) {
                                    is MovieDetails -> media.title
                                    is TvDetails -> media.name
                                    else -> ""
                                },
                                style = MaterialTheme.typography.displaySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            when (media) {
                                is MovieDetails -> if (media.tagline.isNotEmpty()) {
                                    Text(
                                        text = media.tagline,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)

                                    )

                                }

                                is TvDetails -> if (media.tagline.isNotEmpty()) {
                                    Text(
                                        text = media.tagline,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)

                                    )

                                }

                                else -> {}
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            if (validDate != null) {
                                Text(
                                    text = validDate,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                    item {
                        FlowRow(
                            horizontalArrangement = Arrangement.Center,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            when (media) {
                                is MovieDetails -> {
                                    media.genres.forEach { genre ->
                                        GenreChip(
                                            genre = genre
                                        )
                                    }
                                }

                                is TvDetails -> {
                                    media.genres.forEach { genre ->
                                        GenreChip(
                                            genre = genre
                                        )
                                    }
                                }

                                else -> {}

                            }
                        }

                        Spacer(modifier.height(16.dp))

                        HorizontalDivider(
                            thickness = 2.dp,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.outline
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.surfaceTint,
                                    modifier = Modifier.size(28.dp)
                                )
                                Text(
                                    text = "${media.averageVote.toString()} /10",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                            }

                            VerticalDivider(
                                thickness = 2.dp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Text(
                                text = "${media.voteCount.toString()} votes",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }


                    }

                    item {
                        Column(
                            modifier.padding(16.dp)
                        ) {
                            Text(
                                text = media.overview,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(16.dp))


                            when (media) {
                                is MovieDetails -> {
                                    InfoRow(
                                        text = "Runtime",
                                        infoFromState = media.runtime,
                                        isCurrency = false,
                                        isRunTime = true,
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))

                                    InfoRow(
                                        text = "Budget",
                                        infoFromState = media.budget,
                                        isCurrency = true,
                                        isRunTime = false,
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))

                                    InfoRow(
                                        text = "Revenue",
                                        infoFromState = media.revenue,
                                        isCurrency = true,
                                        isRunTime = false,
                                    )

                                }

                                else -> {}
                            }

                        }

                    }

                    when (media) {
                        is MovieDetails -> {

                            item {
                                InfoTabRow(
                                    cast = castWithProfilePicture,
                                    productionCompanies = productionCompaniesWithLogos,
                                    tvCast = null
                                )
                            }

                        }

                        is TvDetails -> {
                            item {
                                InfoTabRow(
                                    cast = null,
                                    tvCast = tvCastWithProfilePicture,
                                    productionCompanies = productionCompaniesWithLogos,
                                )
                            }
                        }

                        else -> {}
                    }
                    when (media) {
                        is MovieDetails -> {
                            item {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    if (media.reviews.isEmpty()) {
                                        null
                                    } else {
                                        Text(
                                            text = stringResource(R.string.reviews),
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        HorizontalDivider(
                                            thickness = 2.dp,
                                            color = MaterialTheme.colorScheme.outline,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }
                            items(
                                items = media.reviews,
                                key = { review -> review.id }) { review ->

                                ReviewComposable(
                                    review = review,
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                        .fillMaxWidth()
                                )
                            }
                        }

                        is TvDetails -> {
                            item {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    if (media.reviews.isEmpty()) {
                                        null
                                    } else {
                                        Text(
                                            text = stringResource(R.string.reviews),
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        HorizontalDivider(
                                            thickness = 2.dp,
                                            color = MaterialTheme.colorScheme.outline,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }
                            items(
                                items = media.reviews,
                                key = { review -> review.id }) { review ->

                                ReviewComposable(
                                    review = review,
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                        .fillMaxWidth()
                                )
                            }
                        }

                        else -> {}
                    }


                }

                IconRow(
                    onBackClick = onBackClick,
                    onAction = onAction,
                    isWatchLater = state.isWatchLater,
                    modifier = Modifier.align(Alignment.TopStart)
                )


            }
            if (isImageLoading) {
                LoadingSpinner()
            }


        }

        Status.ERROR -> {
            Text("Sorry there was an error")

        }
    }
}

@Composable
fun LoadingSpinner() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

fun isDateValid(releaseDate: String?): String? {

    val validDate = releaseDate != null


    if (validDate) {
        val date: LocalDate = LocalDate.parse(releaseDate)
        val formatter = DateTimeFormatter.ofPattern(
            "d MMMM yyyy",
            Locale.getDefault()
        )

        val formattedDate = date.format(formatter)

        return formattedDate.toString()

    } else {
        return null
    }
}


