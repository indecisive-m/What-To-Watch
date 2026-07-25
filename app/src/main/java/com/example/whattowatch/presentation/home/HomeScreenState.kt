package com.example.whattowatch.presentation.home

import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.domain.Movie

data class HomeScreenState(
    val searchQuery: String = "",
    val status: Status = Status.IDLE,
    val upcomingMovies: List<Movie> = emptyList<Movie>(),
    val watchLater: List<Media> = emptyList<Media>(),
    val errorMessage: String? = null,
    val mediaType: MediaType = MediaType.MOVIE

)


enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}

