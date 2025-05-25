package com.example.whattowatch.app

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.whattowatch.presentation.details.DetailsScreenRoot
import com.example.whattowatch.presentation.details.DetailsScreenViewModel
import com.example.whattowatch.presentation.favourites.FavouritesScreenRoot
import com.example.whattowatch.presentation.home.HomeScreenRoot
import com.example.whattowatch.presentation.home.HomeScreenViewModel
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
                startDestination = Route.HomeScreen
            ) {

                composable<Route.HomeScreen>(
                    enterTransition = { slideInVertically() + fadeIn() },
                    exitTransition = { slideOutVertically() + fadeOut() }
                ) {
                    val viewModel = koinViewModel<HomeScreenViewModel>()

                    HomeScreenRoot(
                        viewModel = viewModel,
                        onSearchPress = { searchQuery ->
                            navController.navigate(route = Route.MediaList(searchQuery))
                        },
                        onItemClick = { id ->
                            navController.navigate(route = Route.MediaDetails(id))
                        },
                        onSeeMoreButtonClick = {
                            navController.navigate(route = Route.Favourites)
                        }
                    )
                }


                composable<Route.MediaList>(
                    enterTransition = { fadeIn() },
                    exitTransition = { fadeOut() }
                ) {

                    val viewModel = koinViewModel<SearchResultsViewModel>()

                    SearchResultsScreenRoot(
                        viewModel = viewModel,
                        onItemClick = { id ->

                            Log.d(
                                "Nav",
                                id.toString()
                            )
                            navController.navigate(route = Route.MediaDetails(id))

                        },
                        onSearchResultsClear = {
                            navController.navigateUp()
                        }
                    )
                }

                composable<Route.MediaDetails>(
                    enterTransition = { slideInVertically() + fadeIn() },
                    exitTransition = { slideOutVertically() + fadeOut() }
                ) {

                    val viewModel = koinViewModel<DetailsScreenViewModel>()

                    DetailsScreenRoot(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }

                    )
                }

                composable<Route.Favourites> {

                    FavouritesScreenRoot(
                    )
                }


            }
        }
    }
}