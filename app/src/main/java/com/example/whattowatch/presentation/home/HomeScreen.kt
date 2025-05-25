package com.example.whattowatch.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.presentation.core_components.SearchBar
import com.example.whattowatch.presentation.home.components.UpComingMovies
import com.example.whattowatch.presentation.home.components.YourFavourites
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeScreenViewModel = koinViewModel(),
    onSearchPress: (String) -> Unit,
    onItemClick: (Int) -> Unit,
    onSeeMoreButtonClick: () -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()


    HomeScreen(
        state = state.value,
        onAction = { action ->
            when (action) {
                is HomeScreenAction.OnItemClick -> onItemClick(action.id)
                is HomeScreenAction.OnSeeMoreButtonClick -> onSeeMoreButtonClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        onSearchPress = onSearchPress,
    )

}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit,
    onSearchPress: (String) -> Unit,
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
        SearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { onAction(HomeScreenAction.OnSearchQueryChange(it)) },
            onImeSearch = onSearchPress,
            onSearchClear = { onAction(HomeScreenAction.OnSearchClear) },
            modifier = Modifier.padding(horizontal = 16.dp)
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