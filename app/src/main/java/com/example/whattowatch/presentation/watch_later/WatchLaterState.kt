package com.example.whattowatch.presentation.watch_later

import com.example.whattowatch.domain.Media

data class WatchLaterState(
    val watchLaterItems: List<Media> = emptyList()
)
