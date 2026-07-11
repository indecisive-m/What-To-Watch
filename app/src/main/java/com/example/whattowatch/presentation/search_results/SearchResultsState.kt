package com.example.whattowatch.presentation.search_results

import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.domain.Movie

data class SearchResultsState(
    val searchResults: List<Media> = emptyList<Media>(),
    val searchQuery: String = "",
    val searchResultsStatus: Status = Status.IDLE,
    val upcomingMoviesStatus: Status = Status.IDLE,
    val errorMessage: String? = null,
    val upcomingMovies: List<Movie> = emptyList<Movie>(),
    val mediaType: MediaType = MediaType.MOVIE
)

enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}

