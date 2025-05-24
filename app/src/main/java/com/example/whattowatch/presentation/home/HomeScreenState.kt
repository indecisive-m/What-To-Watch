package com.example.whattowatch.presentation.home

import com.example.whattowatch.domain.Movie

data class HomeScreenState(
    val searchQuery: String = "",
    val status: Status = Status.IDLE,
    val upcomingMovies: List<Movie> = emptyList<Movie>(),
    val errorMessage: String? = null

)


enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}