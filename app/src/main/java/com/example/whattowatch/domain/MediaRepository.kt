package com.example.whattowatch.domain

interface MediaRepository {
    suspend fun searchMovies(query: String): Result<List<Movie>>

    suspend fun searchTVShow(query: String): Result<List<TVShow>>

    suspend fun getImages(imageUrlString: String): Result<ByteArray>
}