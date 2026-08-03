package com.example.whattowatch.presentation.details

import com.example.whattowatch.domain.MediaType

sealed interface DetailsScreenAction {
    data object OnFavouriteClick : DetailsScreenAction
    data class OnItemClick(val id: Int, val mediaType: MediaType) : DetailsScreenAction
}