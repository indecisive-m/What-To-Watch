package com.example.whattowatch.domain

import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun searchMovies(query: String): Result<List<Movie>>

    suspend fun searchTv(query: String): Result<List<Tv>>

    suspend fun getMovieDetails(id: Int): Result<MovieDetails>

    suspend fun getTvDetails(id: Int): Result<TvDetails>

    suspend fun getImages(imageUrlString: String): Result<ByteArray>

    suspend fun getUpcomingMovies(): Result<List<Movie>>

    fun getAllFavourites(): Flow<List<Media>>

    suspend fun addToFavourites(media: Media)

    suspend fun deleteFromFavorites(id: Int)

    fun isBookFavourited(id: Int): Flow<Boolean>

}