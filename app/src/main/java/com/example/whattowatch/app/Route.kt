package com.example.whattowatch.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MediaGraph : Route

    @Serializable
    data object HomeScreen : Route

    @Serializable
    data class MediaList(val searchQuery: String) : Route

    @Serializable
    data class MediaDetails(val id: Int) : Route

    @Serializable
    data object Favourites : Route
}