package com.example.whattowatch.presentation.search_results

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.presentation.search_results.components.ItemCard
import com.example.whattowatch.presentation.search_results.components.SearchBar
import com.example.whattowatch.presentation.search_results.components.UpComingMovies
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchResultsScreenRoot(
    viewModel: SearchResultsViewModel = koinViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle()



    SearchResultsScreen(
        state = state.value,
        onAction = { action ->
            when (action) {
                is SearchResultsAction.OnItemClick -> {

                }

                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}


@Composable
fun SearchResultsScreen(
    state: SearchResultsState,
    onAction: (SearchResultsAction) -> Unit

) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AnimatedVisibility(
            visible = state.searchResults.isEmpty(),

            exit = shrinkOut() + shrinkVertically()

        ) {
            UpComingMovies(
                items = state.upcomingMovies,
                loadUpComingMovies = { onAction(SearchResultsAction.LoadUpcomingMovieData) },
                status = state.upcomingMoviesStatus,
                modifier = Modifier

            )

        }


        Column(
            modifier = Modifier
                .padding(
                    vertical = if (state.searchResults.isNotEmpty()) 50.dp else 8.dp,
                    horizontal = 16.dp
                )
        ) {
            
            SearchBar(
                searchQuery = state.searchQuery,
                onSearchQueryChange = {
                    onAction(SearchResultsAction.OnSearchQueryChange(it))
                },
                onImeSearch = {
                    onAction(SearchResultsAction.OnSearchPress(it))
                },
                onSearchClear = {
                    onAction(SearchResultsAction.OnSearchClear)
                },
                modifier = Modifier,
            )
            Spacer(Modifier.height(16.dp))

            AnimatedVisibility(
                visible = state.searchResults.isNotEmpty(),
                enter = slideInHorizontally() + fadeIn(),
                exit = slideOutHorizontally() + fadeOut()
            )
            {
                Box() {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.searchResults) { searchResult ->
                            ItemCard(
                                item = searchResult
                            )

                        }
                    }
                    SmallFloatingActionButton(
                        onClick = { onAction(SearchResultsAction.OnSearchResultsClear) },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear results"
                        )
                    }

                }
            }

        }
    }
}


//@Preview
//@Composable
//fun SearchResultsScreenPreview() {
//    WhatToWatchTheme {
//        SearchResultsScreen(
//
//        )
//    }
//}