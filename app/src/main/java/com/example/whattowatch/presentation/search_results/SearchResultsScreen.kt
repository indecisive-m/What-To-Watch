package com.example.whattowatch.presentation.search_results

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.R
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.presentation.core_components.SearchBar
import com.example.whattowatch.presentation.core_components.SearchOptionsTabRow
import com.example.whattowatch.presentation.details.LoadingSpinner
import com.example.whattowatch.presentation.search_results.components.ItemCard
import org.koin.androidx.compose.koinViewModel

// This needs to reset to top of scrollable list when a new search is pressed.
// Currently, it remembers where it was in the list which is not correct

@Composable
fun SearchResultsScreenRoot(
    viewModel: SearchResultsViewModel = koinViewModel(),
    onItemClick: (Int, MediaType) -> Unit,
    onSearchResultsClear: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()



    SearchResultsScreen(
        state = state.value,
        onAction = { action ->
            when (action) {
                is SearchResultsAction.OnItemClick -> onItemClick(action.id, action.mediaType)
                else -> Unit
            }
            viewModel.onAction(action)
        },
        onSearchResultsClear = onSearchResultsClear
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(
    state: SearchResultsState,
    onSearchResultsClear: () -> Unit,
    onAction: (SearchResultsAction) -> Unit

) {
    val screenConfiguration = LocalConfiguration.current

    val isLandscape = screenConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE

//    val scrollable = if (isLandscape) Modifier.verticalScroll(rememberScrollState()) else Modifier
    when (state.searchResultsStatus) {
        Status.LOADING, Status.IDLE -> {
            LoadingSpinner()

        }

        Status.SUCCESS -> {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

                SearchBar(
                    searchQuery = state.searchQuery,
                    onSearchQueryChange = {
                        onAction(SearchResultsAction.OnSearchQueryChange(it))
                    },
                    onImeSearch = {
                        onAction(
                            SearchResultsAction.OnSearchClick(
                                state.searchQuery,
                                state.mediaType
                            )
                        )
                    },
                    onSearchClear = {
                        onAction(SearchResultsAction.OnSearchClear)
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                SearchOptionsTabRow(
                    searchOption = state.mediaType,
                    onSearchOptionClick = { onAction(SearchResultsAction.OnSearchOptionClick(it)) },
                    modifier = Modifier
                )

                Spacer(Modifier.height(16.dp))



                AnimatedVisibility(
                    visible = state.searchResults.isEmpty() && state.searchQuery.isNotBlank(),
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    Text(
                        text = stringResource(R.string.no_search_results_found),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

                AnimatedVisibility(
                    visible = state.searchResults.isNotEmpty(),
                    enter = slideInHorizontally() + fadeIn(),
                    exit = slideOutHorizontally() + fadeOut()
                )
                {
                    Box {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(state.searchResults) { searchResult ->
                                ItemCard(
                                    item = searchResult,
                                    onClick = {
                                        onAction(
                                            SearchResultsAction.OnItemClick(
                                                searchResult.id,
                                                mediaType = searchResult.mediaType
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        Status.ERROR -> {
            Text("Sorry there was an error")

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