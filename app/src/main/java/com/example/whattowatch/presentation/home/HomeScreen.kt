package com.example.whattowatch.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.presentation.core_components.SearchBar
import com.example.whattowatch.presentation.core_components.SearchOptionsTabRow
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
        .background(MaterialTheme.colorScheme.background),


    ) {

    LaunchedEffect(Unit) {
        onAction(HomeScreenAction.OnSearchClear)
    }

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            SearchBar(
                searchQuery = state.searchQuery,
                onSearchQueryChange = { onAction(HomeScreenAction.OnSearchQueryChange(it)) },
                onImeSearch = { onSearchClick(state.searchQuery, state.mediaType) },
                onSearchClear = { onAction(HomeScreenAction.OnSearchClear) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            SearchOptionsTabRow(
                searchOption = state.mediaType,
                onSearchOptionClick = { onAction(HomeScreenAction.OnSearchOptionClick(it)) }
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

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
}