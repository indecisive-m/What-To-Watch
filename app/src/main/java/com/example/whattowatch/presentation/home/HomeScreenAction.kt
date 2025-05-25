package com.example.whattowatch.presentation.home

sealed interface HomeScreenAction {
    data class OnSearchClick(val query: String) : HomeScreenAction
    data class OnItemClick(val id: Int) : HomeScreenAction
    data class OnSearchQueryChange(val query: String) : HomeScreenAction
    data object OnSeeMoreButtonClick : HomeScreenAction

    data object OnSearchClear : HomeScreenAction


}