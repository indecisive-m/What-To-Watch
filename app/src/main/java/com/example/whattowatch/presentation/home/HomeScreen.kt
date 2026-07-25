package com.example.whattowatch.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.presentation.home.components.UpComingMovies
import com.example.whattowatch.presentation.home.components.YourFavourites
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



    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {


        UpComingMovies(
            items = state.upcomingMovies,
            status = state.status,
            onItemClick = onAction,
            modifier = Modifier
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        AnimatedVisibility(
            visible = state.favourites.isNotEmpty()
        ) {

            YourFavourites(
                favourites = state.favourites,
                onItemClick = onAction,
                onSeeMoreButtonClick = onAction
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        //TODO: Maybe add categories?

//        Text("Categories")
        Spacer(modifier = Modifier.height(50.dp))

    }

}
