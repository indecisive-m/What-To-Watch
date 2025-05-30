package com.example.whattowatch.presentation.search_results

import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.Movie

data class SearchResultsState(
    val searchResults: List<Media> = emptyList<Media>(),
    val searchQuery: String = "",
    val searchResultsStatus: Status = Status.IDLE,
    val upcomingMoviesStatus: Status = Status.IDLE,
    val searchOption: SearchOption = SearchOption.MOVIE,
    val errorMessage: String? = null,
    val upcomingMovies: List<Movie> = emptyList<Movie>()
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