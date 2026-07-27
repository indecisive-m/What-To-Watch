package com.example.whattowatch.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.presentation.home.components.Popular
import com.example.whattowatch.presentation.home.components.TopRated
import com.example.whattowatch.presentation.home.components.UpComingMovies
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeScreenViewModel = koinViewModel(),
    onSearchClick: (String, MediaType) -> Unit,
    onItemClick: (Int, MediaType) -> Unit,
    onSeeMoreButtonClick: () -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()


    HomeScreen(
        state = state.value,
        onAction = { action ->
            when (action) {
                is HomeScreenAction.OnItemClick -> onItemClick(action.id, action.mediaType)
                is HomeScreenAction.OnSeeMoreButtonClick -> onSeeMoreButtonClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        onSearchClick = onSearchClick,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )


}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit,
    onSearchClick: (String, MediaType) -> Unit,
    modifier: Modifier = Modifier


) {

    LaunchedEffect(Unit) {
        onAction(HomeScreenAction.OnSearchClear)
    }

    when (state.status) {
        Status.IDLE, Status.LOADING -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Status.ERROR -> {
            Text("There is an error")
        }

        Status.SUCCESS -> {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
            ) {


                UpComingMovies(
                    items = state.upcomingMovies,
                    onItemClick = onAction,
                    modifier = Modifier
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                TopRated(
                    topRated = state.topRatedMovies,
                    onClick = onAction,
                    modifier = Modifier

                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                Popular(
                    popular = state.popularMovies,
                    onClick = onAction,
                    modifier = Modifier

                )



                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                TopRated(
                    topRated = state.topRatedTvShows,
                    onClick = onAction,
                    modifier = Modifier
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                Popular(
                    popular = state.popularTvShows,
                    onClick = onAction,
                    modifier = Modifier
                )


                //TODO: Maybe add categories?

//        Text("Categories")

            }

        }

    }


}
