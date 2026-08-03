package com.example.whattowatch.app

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.whattowatch.presentation.details.DetailsScreenRoot
import com.example.whattowatch.presentation.details.DetailsScreenViewModel
import com.example.whattowatch.presentation.home.HomeScreenRoot
import com.example.whattowatch.presentation.home.HomeScreenViewModel
import com.example.whattowatch.presentation.search_results.SearchResultsScreenRoot
import com.example.whattowatch.presentation.search_results.SearchResultsViewModel
import com.example.whattowatch.presentation.watch_later.WatchLaterScreenRoot
import com.example.whattowatch.presentation.watch_later.WatchLaterViewModel
import com.example.whattowatch.ui.theme.WhatToWatchTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {
    WhatToWatchTheme {
        val navController = rememberNavController()


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: BottomNavigation.HOME
        val currentDestination = navBackStackEntry?.destination

        val currentHierarchy = navBackStackEntry?.destination?.hierarchy


        Scaffold(
            contentWindowInsets = WindowInsets.systemBars,
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            bottomBar = {

                val screensWithBottomBar = currentDestination?.hasRoute<Route.HomeScreen>() == true
                        || currentDestination?.hasRoute<Route.WatchLater>() == true
                        || currentDestination?.hasRoute<Route.MediaList>() == true

                if (screensWithBottomBar) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()


                    ) {
                        NavigationBar(
                            windowInsets = NavigationBarDefaults.windowInsets,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth(0.75F)
                                .clip(RoundedCornerShape(36.dp)),

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
                                        navController.navigate(navigation.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true

                                            restoreState = true

                                        }
                                    },
                                    colors = NavigationBarItemColors(
                                        selectedIndicatorColor = MaterialTheme.colorScheme.primary,
                                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                                        unselectedIconColor = MaterialTheme.colorScheme.secondary,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                                        disabledIconColor = MaterialTheme.colorScheme.background,
                                        disabledTextColor = MaterialTheme.colorScheme.background,
                                    ),
                                    icon = {

                                        Icon(
                                            imageVector = navigation.icon,
                                            contentDescription = navigation.label,
                                            modifier = Modifier.size(28.dp),
                                        )

                                    },
                                    label = {
                                        Text(
                                            text = navigation.label,
                                        )
                                    }
                                )

                            }
                        }

                    }
                } else {

                    null
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
                                navController.navigate(route = Route.WatchLater)
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
                            },
                            onItemClick = { id, mediaType ->
                                navController.navigate(route = Route.MediaDetails(id, mediaType))
                            },

                            )
                    }

                    composable<Route.WatchLater> {

                        val viewModel = koinViewModel<WatchLaterViewModel>()


                        WatchLaterScreenRoot(
                            viewModel = viewModel,
                            onItemClick = { id, mediaType ->

                                navController.navigate(route = Route.MediaDetails(id, mediaType))

                            },
                        )
                    }


                }
            }
        }
    }
}