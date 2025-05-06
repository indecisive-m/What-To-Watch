package com.example.whattowatch.domain

interface MediaRepository {
    suspend fun searchMovies(query: String): Result<List<Movie>>
}