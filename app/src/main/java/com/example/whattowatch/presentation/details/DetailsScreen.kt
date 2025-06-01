package com.example.whattowatch.presentation.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.MovieDetails
import com.example.whattowatch.presentation.details.components.CastRow
import com.example.whattowatch.presentation.details.components.GenreChip
import com.example.whattowatch.presentation.details.components.IconButtonComposable
import com.example.whattowatch.presentation.details.components.InfoRow
import com.example.whattowatch.presentation.details.components.ProductionCompaniesRow
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreenRoot(
    viewModel: DetailsScreenViewModel = koinViewModel<DetailsScreenViewModel>(),
    onBackClick: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    DetailsScreen(
        state = state.value,
        onBackClick = onBackClick
    )

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(
    state: DetailsScreenState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {


    when (state.status) {
        Status.IDLE, Status.LOADING -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Status.SUCCESS -> {

            if (state.media == null) {
                return
            }

            if (state.media is MovieDetails) {
                MovieDetailsComposable(
                    onBackClick = onBackClick,
                    state = state.media,
                    modifier = Modifier
                )

            }


        }

        Status.ERROR -> {
            Text("Sorry there was an error")

        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieDetailsComposable(
    onBackClick: () -> Unit,
    state: MovieDetails,
    modifier: Modifier = Modifier
) {
    val fallbackImage = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    var currentUrl = state.posterPath ?: fallbackImage


    val productionCompaniesWithLogos = state.productionCompanies.filter { it.logoPath != null }

    val castWithProfilePicture = state.credits.cast.filter { it.profilePath != null }


    Box(
        modifier = modifier
            .fillMaxSize()

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
                    onError = {
                        if (currentUrl != fallbackImage) {
                            currentUrl = fallbackImage
                        }
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
                        text = state.title,
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

                    if (isDateValid(state.releaseDate) != null) {
                        Text(
                            text = isDateValid(state.releaseDate).toString(),
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

                    Spacer(modifier = Modifier.height(8.dp))

                    ProductionCompaniesRow(
                        productionCompanies = productionCompaniesWithLogos
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CastRow(
                        cast = castWithProfilePicture,
                    )


                }

            }


        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,

            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(8.dp)


        ) {
            IconButtonComposable(
                icon = Icons.AutoMirrored.Default.ArrowBack,
                onClick = onBackClick
            )
            IconButtonComposable(
                icon = Icons.Filled.FavoriteBorder,
                onClick = onBackClick
            )

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun isDateValid(releaseDate: String): String? {

    if (releaseDate.isNotEmpty()) {

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


