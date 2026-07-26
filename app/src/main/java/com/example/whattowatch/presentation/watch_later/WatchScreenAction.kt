package com.example.whattowatch.presentation.watch_later

import com.example.whattowatch.domain.MediaType

interface WatchScreenAction {
    data class OnItemClick(val id: Int, val mediaType: MediaType) : WatchScreenAction
}