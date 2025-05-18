package com.example.whattowatch.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.whattowatch.presentation.search_results.SearchResultsScreenRoot
import com.example.whattowatch.presentation.search_results.SearchResultsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.MediaGraph
        ) {

            navigation<Route.MediaGraph>(
                startDestination = Route.MediaList
            ) {
                composable<Route.MediaList> {

                    val viewModel = koinViewModel<SearchResultsViewModel>()

                    SearchResultsScreenRoot(
                        viewModel = viewModel
                    )
                }


            }
        }
    }
}