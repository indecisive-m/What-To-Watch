package com.example.whattowatch.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MediaGraph : Route

    @Serializable
    data object MediaList : Route

    @Serializable
    data class MediaDetails(val id: Int) : Route
}