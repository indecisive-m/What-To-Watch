package com.example.whattowatch.presentation.search_results

import com.example.whattowatch.domain.Media

data class SearchResultsState(
    val searchResults: List<Media> = emptyList<Media>(),
    val searchQuery: String = "",
    val status: Status = Status.IDLE,
    val searchOption: SearchOption = SearchOption.MOVIE,
    val errorMessage: String? = null,
)

enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}

enum class SearchOption {
    MOVIE,
    TV
}