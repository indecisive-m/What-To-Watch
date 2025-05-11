package com.example.whattowatch.presentation.search_results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.presentation.search_results.components.ItemCard
import com.example.whattowatch.presentation.search_results.components.SearchBar
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
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 50.dp,
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
                onAction(SearchResultsAction.OnSearchClear())
            },
            modifier = Modifier,
        )
        Spacer(Modifier.height(16.dp))
        if (state.searchResults.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.searchResults) { searchResult ->
                    ItemCard(
                        item = searchResult
                    )


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