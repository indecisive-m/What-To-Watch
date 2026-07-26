package com.example.whattowatch.presentation.home

import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.Tv

data class HomeScreenState(
    val searchQuery: String = "",
    val status: Status = Status.IDLE,
    val upcomingMovies: List<Movie> = emptyList<Movie>(),
    val popularMovies: List<Movie> = emptyList<Movie>(),
    val topRatedMovies: List<Movie> = emptyList<Movie>(),
    val nowPlayingMovies: List<Movie> = emptyList<Movie>(),

    val topRatedTvShows: List<Tv> = emptyList<Tv>(),
    val popularTvShows: List<Tv> = emptyList<Tv>(),
    val upcomingMoviesError: String? = null,
    val popularMoviesError: String? = null,
    val topRatedMoviesError: String? = null,
    val nowPlayingMoviesError: String? = null,

    val topRatedTvShowsError: String? = null,
    val popularTvShowsError: String? = null,

    val errorMessage: String? = null,
    val mediaType: MediaType = MediaType.MOVIE

)


enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}

