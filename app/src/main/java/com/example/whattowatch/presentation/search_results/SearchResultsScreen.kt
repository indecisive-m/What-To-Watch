package com.example.whattowatch.presentation.search_results

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.whattowatch.ui.theme.WhatToWatchTheme


@Composable
fun SearchResultsScreenRoot(
    modifier: Modifier = Modifier
) {

    SearchResultsScreen()

}


@Composable
fun SearchResultsScreen() {
    Text("Hello")

}


@Preview
@Composable
fun SearchResultsScreenPreview() {
    WhatToWatchTheme {
        SearchResultsScreen()
    }
}