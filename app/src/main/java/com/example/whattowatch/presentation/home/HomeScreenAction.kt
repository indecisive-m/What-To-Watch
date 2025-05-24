package com.example.whattowatch.presentation.home

sealed interface HomeScreenAction {
    data class OnSearchPress(val query: String) : HomeScreenAction
    data class OnItemClick(val id: Int) : HomeScreenAction
    data class OnSearchQueryChange(val query: String) : HomeScreenAction
    data object OnSearchClear : HomeScreenAction


}