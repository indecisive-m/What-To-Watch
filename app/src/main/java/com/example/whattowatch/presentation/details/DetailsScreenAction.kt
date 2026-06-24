package com.example.whattowatch.presentation.details

sealed interface DetailsScreenAction {
    data object OnFavouriteClick : DetailsScreenAction
}