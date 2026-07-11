package com.example.whattowatch.app

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
    data object Favourites : Route
}