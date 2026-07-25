package com.example.whattowatch.domain

import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun searchMovies(query: String): Result<List<Movie>>

    suspend fun searchTv(query: String): Result<List<Tv>>

    suspend fun getMovieDetails(id: Int): Result<MovieDetails>

    suspend fun getTvDetails(id: Int): Result<TvDetails>

    suspend fun getUpcomingMovies(): Result<List<Movie>>

    suspend fun getNowPlayingMovies(): Result<List<Movie>>

    suspend fun getPopularMovies(): Result<List<Movie>>

    suspend fun getTopRatedMovies(): Result<List<Movie>>

    suspend fun getTopRatedTvShows(): Result<List<Tv>>

    suspend fun getPopularTvShows(): Result<List<Tv>>


    fun getAllWatchLater(): Flow<List<Media>>

    suspend fun addToWatchLater(media: Media)

    suspend fun deleteFromWatchLater(id: Int, mediaType: MediaType)


    fun isMediaInWatchLater(id: Int): Flow<Boolean>

}