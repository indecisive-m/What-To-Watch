package com.example.whattowatch.domain

interface MediaRepository {
    suspend fun searchMovies(query: String): Result<List<Movie>>

    suspend fun searchTv(query: String): Result<List<Tv>>

    suspend fun getMovieDetails(id: Int): Result<List<MovieDetails>>

    suspend fun getTvDetails(id: Int): Result<List<TvDetails>>

    suspend fun getImages(imageUrlString: String): Result<ByteArray>

}