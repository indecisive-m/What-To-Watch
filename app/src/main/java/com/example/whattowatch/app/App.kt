package com.example.whattowatch.app

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.whattowatch.presentation.details.DetailsScreenRoot
import com.example.whattowatch.presentation.details.DetailsScreenViewModel
import com.example.whattowatch.presentation.favourites.FavouritesScreenRoot
import com.example.whattowatch.presentation.home.HomeScreenRoot
import com.example.whattowatch.presentation.home.HomeScreenViewModel
import com.example.whattowatch.presentation.search_results.SearchResultsScreenRoot
import com.example.whattowatch.presentation.search_results.SearchResultsViewModel
import com.example.whattowatch.ui.theme.WhatToWatchTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {
    WhatToWatchTheme {
        val navController = rememberNavController()


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: BottomNavigation.HOME

        val currentHierarchy = navBackStackEntry?.destination?.hierarchy

        Scaffold(
            contentWindowInsets = WindowInsets.systemBars,
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            bottomBar = {
                NavigationBar(
                    windowInsets = NavigationBarDefaults.windowInsets
                ) {
                    BottomNavigation.entries.forEachIndexed { index, navigation ->

                        val isSelected by remember(currentRoute) {
                            derivedStateOf {
                                currentHierarchy?.any { it.hasRoute(navigation.route::class) }
                                    ?: true
                            }

                        }
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(navigation.route)
                            },
                            icon = {

                                Icon(
                                    imageVector = navigation.icon,
                                    contentDescription = navigation.label,
                                    modifier = Modifier.size(28.dp)
                                )

                            }
                        )

                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.MediaGraph,
                modifier = Modifier.padding(innerPadding)
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
                            onSearchClick = { searchQuery, mediaType ->
                                navController.navigate(
                                    route = Route.MediaList(
                                        searchQuery,
                                        mediaType
                                    )
                                )
                            },
                            onItemClick = { id, mediaType ->
                                navController.navigate(route = Route.MediaDetails(id, mediaType))
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
                            onItemClick = { id, mediaType ->

                                Log.d(
                                    "Nav",
                                    id.toString()
                                )
                                navController.navigate(route = Route.MediaDetails(id, mediaType))

                            },
                            onSearchResultsClear = {
                                navController.navigate(route = Route.HomeScreen)
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
}