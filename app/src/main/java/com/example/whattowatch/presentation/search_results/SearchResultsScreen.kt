package com.example.whattowatch.presentation.search_results

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
            .padding(50.dp)
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                onAction(SearchResultsAction.OnSearchQueryChange(it))
            },
            placeholder = {
                Text("Write Summat Here")
            }
        )
        Button(
            onClick = {
                onAction(SearchResultsAction.OnSearchPress(state.searchQuery))
            }
        ) { }

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