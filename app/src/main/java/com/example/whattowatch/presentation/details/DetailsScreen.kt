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
import com.example.whattowatch.presentation.details.components.CreatedByRow
import com.example.whattowatch.presentation.details.components.GenreChip
import com.example.whattowatch.presentation.details.components.IconRow
import com.example.whattowatch.presentation.details.components.InfoRow
import com.example.whattowatch.presentation.details.components.InfoTabRow
import com.example.whattowatch.presentation.details.components.ProductionCompaniesRow
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

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state.status) {
            Status.IDLE, Status.LOADING -> {

                LoadingSpinner()
            }

            Status.SUCCESS -> {


                if (state.media == null) {
                    return
                }


                if (state.media is MovieDetails) {
                    MovieDetailsComposable(
                        onBackClick = onBackClick,
                        onImageLoaded = { isImageLoading = false },
                        state = state.media,
                        isWatchLater = state.isWatchLater,
                        onAction = onAction,
                        modifier = Modifier
                    )

                }

                if (state.media is TvDetails) {
                    TvDetailsComposable(
                        onBackClick = onBackClick,
                        onImageLoaded = { isImageLoading = false },
                        state = state.media,
                        isWatchLater = state.isWatchLater,
                        onAction = onAction,
                        modifier = Modifier
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
}


@Composable
fun TvDetailsComposable(
    onBackClick: () -> Unit,
    onImageLoaded: () -> Unit,
    onAction: (DetailsScreenAction) -> Unit,
    state: TvDetails,
    isWatchLater: Boolean,
    modifier: Modifier = Modifier
) {
    val fallbackImage =
        "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    var currentUrl by remember(state.posterPath) {
        mutableStateOf(state.posterPath ?: fallbackImage)
    }

    val productionCompaniesWithLogos = state.productionCompanies.filter { it.logoPath != null }

    val creatorsWithImage = state.createdBy.sortedByDescending { it.profilePath != null }




    Box(
        modifier = modifier.fillMaxSize()

    ) {
        LazyColumn(
            modifier = Modifier
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
                        onImageLoaded()
                    },
                    onError = {
                        if (currentUrl != fallbackImage) {
                            currentUrl = fallbackImage
                        }
                        onImageLoaded()
                    },
                    modifier = Modifier.fillMaxWidth()
                )

            }
            item {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
                ) {
                    Text(
                        text = state.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (state.tagline.isNotEmpty()) {
                        Text(
                            text = state.tagline,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)

                        )

                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    if (isDateValid(state.firstAirDate) != null) {
                        Text(
                            text = isDateValid(state.firstAirDate).toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.Center,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        state.genres.map { genre ->
                            GenreChip(
                                genre = genre
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))



                    Text(
                        text = state.overview,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    Text("${state.averageVote.toString()} average vote")
                    Text("${state.voteCount.toString()} vote count")

                    Spacer(modifier = Modifier.height(8.dp))

                    InfoRow(
                        text = "Number of Seasons",
                        infoFromState = state.numberOfSeasons,
                        isCurrency = false,
                        isRunTime = false,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    InfoRow(
                        text = "Total Number of Episodes",
                        infoFromState = state.numberOfEpisodes,
                        isCurrency = false,
                        isRunTime = false
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    if (isDateValid(state.lastAirDate) != null) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Last Air Date: ",
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = isDateValid(state.lastAirDate).toString()
                            )

                        }
                    }






                    Spacer(modifier = Modifier.height(8.dp))

                    ProductionCompaniesRow(
                        productionCompanies = productionCompaniesWithLogos
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CreatedByRow(
                        creators = creatorsWithImage,
                    )


                }

            }


        }
        IconRow(
            onBackClick = onBackClick,
            onAction = onAction,
            isWatchLater = isWatchLater,
            modifier = Modifier.align(Alignment.TopStart)
        )
    }
}


@Composable
fun MovieDetailsComposable(
    onBackClick: () -> Unit,
    onImageLoaded: () -> Unit,
    onAction: (DetailsScreenAction) -> Unit,
    state: MovieDetails,
    isWatchLater: Boolean,

    modifier: Modifier = Modifier
) {
    val fallbackImage =
        "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    var currentUrl by remember(state.posterPath) {
        mutableStateOf(state.posterPath ?: fallbackImage)
    }


    val productionCompaniesWithLogos =
        remember(state.productionCompanies) { state.productionCompanies.filter { it.logoPath != null } }

    val castWithProfilePicture =
        remember(state.credits) { state.credits.cast.filter { it.profilePath != null } }



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
                        onImageLoaded()
                    },
                    onError = {
                        if (currentUrl != fallbackImage) {
                            currentUrl = fallbackImage
                        }
                        onImageLoaded()
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
                        text = state.title,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (state.tagline.isNotEmpty()) {
                        Text(
                            text = state.tagline,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.align(Alignment.CenterHorizontally)

                        )

                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    if (isDateValid(state.releaseDate) != null) {
                        Text(
                            text = isDateValid(state.releaseDate).toString(),
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
                    state.genres.forEach { genre ->
                        GenreChip(
                            genre = genre
                        )

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
                            text = "${state.averageVote.toString()} /10",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                    }

                    VerticalDivider(
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = "${state.voteCount.toString()} votes",
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
                        text = state.overview,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))




                    InfoRow(
                        text = "Runtime",
                        infoFromState = state.runtime,
                        isCurrency = false,
                        isRunTime = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    InfoRow(
                        text = "Budget",
                        infoFromState = state.budget,
                        isCurrency = true,
                        isRunTime = false,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    InfoRow(
                        text = "Revenue",
                        infoFromState = state.revenue,
                        isCurrency = true,
                        isRunTime = false,
                    )

                }

            }

            item {
                InfoTabRow(
                    castWithProfilePicture,
                    productionCompaniesWithLogos,

                    )
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    if (state.reviews.isEmpty()) {
                        null
                    } else {
                        Text(
                            text = stringResource(R.string.reviews),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge,
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
                state.reviews,
                key = { review -> review.id }) { review ->

                ReviewComposable(
                    review = review,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )

            }
        }

        IconRow(
            onBackClick = onBackClick,
            onAction = onAction,
            isWatchLater = isWatchLater,
            modifier = Modifier.align(Alignment.TopStart)
        )


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


