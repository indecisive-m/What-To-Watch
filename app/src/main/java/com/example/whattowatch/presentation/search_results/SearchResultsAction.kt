package com.example.whattowatch.presentation.search_results

import com.example.whattowatch.domain.MediaType

sealed interface SearchResultsAction {
    data class OnSearchQueryChange(val query: String) : SearchResultsAction
    data class OnItemClick(val id: Int, val mediaType: MediaType) : SearchResultsAction
    data class OnSearchClick(val query: String, val mediaType: MediaType) : SearchResultsAction

    data class OnSearchOptionClick(val mediaType: MediaType) : SearchResultsAction
    data object OnSearchClear : SearchResultsAction
    data object OnSearchResultsClear : SearchResultsAction
}