package com.example.whattowatch.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.presentation.core_components.SearchBar
import com.example.whattowatch.presentation.home.components.UpComingMovies
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeScreenViewModel = koinViewModel(),
    onSearchPress: (String) -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()


    HomeScreen(
        state = state.value,
        onAction = { action ->
            when (action) {
                is HomeScreenAction.OnItemClick -> onItemClick(action.id)
                else -> Unit
            }
            viewModel.onAction(action)
        },
        onSearchPress = onSearchPress
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
    Column() {
        UpComingMovies(
            items = state.upcomingMovies,
            status = state.status,
            onItemClick = onAction,
            modifier = Modifier
        )
        SearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { onAction(HomeScreenAction.OnSearchQueryChange(it)) },
            onImeSearch = onSearchPress,
            onSearchClear = { onAction(HomeScreenAction.OnSearchClear) },
            modifier = Modifier.padding(16.dp),
        )
    }

}