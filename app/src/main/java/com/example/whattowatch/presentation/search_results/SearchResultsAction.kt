package com.example.whattowatch.presentation.search_results

sealed interface SearchResultsAction {
    data class OnSearchQueryChange(val query: String) : SearchResultsAction
    data class OnItemClick(val id: Int) : SearchResultsAction
    data class OnSearchPress(val query: String) : SearchResultsAction
}