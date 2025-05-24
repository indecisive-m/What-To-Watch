package com.example.whattowatch.presentation.details

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.R
import com.example.whattowatch.domain.MovieDetails
import org.koin.androidx.compose.koinViewModel

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
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(50.dp)
                ) {


                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                    Text(state.media.id.toString())
                    Text(state.media.averageVote.toString())
                    Text(state.media.backdropPath.toString())
                    Text(state.media.overview)
                    Text(state.media.posterPath.toString())
                    Text(state.media.adult.toString())
                    Text(state.media.language)
                    Text(state.media.popularity.toString())
                    Text(state.media.voteCount.toString())
                    Text(state.media.title)

                    state.media.productionCompanies.map { company ->
                        Text(company.name)
                        Text(company.logoPath.toString())
                    }

                }
            }


            Log.d(
                "detailsScreen",
                state.media.id.toString()
            )


        }

        Status.ERROR -> {
            Text("Sorry there was an error")

        }
    }

}