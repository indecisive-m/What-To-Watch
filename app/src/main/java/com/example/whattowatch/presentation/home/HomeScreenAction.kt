package com.example.whattowatch.presentation.home

import com.example.whattowatch.domain.MediaType

sealed interface HomeScreenAction {
    data class OnSearchClick(val query: String, val mediaType: MediaType) : HomeScreenAction
    data class OnItemClick(val id: Int, val mediaType: MediaType) : HomeScreenAction
    data class OnSearchQueryChange(val query: String) : HomeScreenAction
    data object OnSeeMoreButtonClick : HomeScreenAction

    data class OnSearchOptionClick(val mediaType: MediaType) : HomeScreenAction

    data object OnSearchClear : HomeScreenAction


}