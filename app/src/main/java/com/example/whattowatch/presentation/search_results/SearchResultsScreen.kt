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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whattowatch.presentation.core_components.SearchBar
import com.example.whattowatch.presentation.search_results.components.ItemCard
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchResultsScreenRoot(
    viewModel: SearchResultsViewModel = koinViewModel(),
    onItemClick: (Int) -> Unit,
    onSearchResultsClear: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()



    SearchResultsScreen(
        state = state.value,
        onAction = { action ->
            when (action) {
                is SearchResultsAction.OnItemClick -> onItemClick(action.id)
                else -> Unit
            }
            viewModel.onAction(action)
        },
        onSearchResultsClear = onSearchResultsClear
    )

}


@Composable
fun SearchResultsScreen(
    state: SearchResultsState,
    onSearchResultsClear: () -> Unit,
    onAction: (SearchResultsAction) -> Unit

) {
    val screenConfiguration = LocalConfiguration.current

    val isLandscape = screenConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE

//    val scrollable = if (isLandscape) Modifier.verticalScroll(rememberScrollState()) else Modifier

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(
                    innerPadding
                )
                .padding(horizontal = 16.dp)
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
                visible = state.searchResultsStatus == Status.LOADING
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }


            AnimatedVisibility(
                visible = state.searchResultsStatus == Status.SUCCESS && state.searchResults.isEmpty() && state.searchQuery.isNotBlank(),
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Text("No movies found")
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
                                onClick = { onAction(SearchResultsAction.OnItemClick(searchResult.id)) }
                            )

                        }
                    }


                    SmallFloatingActionButton(
                        onClick = { onSearchResultsClear() },
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