package com.example.whattowatch.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.whattowatch.domain.MediaType
import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MediaGraph : Route

    @Serializable
    data object HomeScreen : Route

    @Serializable
    data class MediaList(val searchQuery: String, val mediaType: MediaType) : Route

    @Serializable
    data class MediaDetails(val id: Int, val mediaType: MediaType) : Route

    @Serializable
    data object WatchLater : Route
}

enum class BottomNavigation(
    val label: String,
    val icon: ImageVector,
    val route: Route
) {
    HOME(
        label = "Home",
        icon = Icons.Filled.Home,
        route = Route.HomeScreen
    ),

    WATCH_LATER(
        label = "Watch Later",
        icon = Icons.Filled.Star,
        route = Route.WatchLater
    ),
    SEARCH(
        label = "Search",
        icon = Icons.Filled.Search,
        route = Route.MediaList(
            "", MediaType.MOVIE
        )
    ),


}