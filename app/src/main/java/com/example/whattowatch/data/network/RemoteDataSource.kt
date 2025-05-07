package com.example.whattowatch.data.network

import com.example.whattowatch.data.dto.MovieDetailsDto
import com.example.whattowatch.data.dto.MovieSearchResultsDto
import com.example.whattowatch.data.dto.TVSearchResultsDto

interface RemoteDataSource {
    suspend fun searchMovies(
        query: String
    ): Result<MovieSearchResultsDto>

    suspend fun getImages(imageUrlString: String): Result<ByteArray>

    suspend fun searchTVShow(query: String): Result<TVSearchResultsDto>

    suspend fun getMovieDetails(id: Int): Result<MovieDetailsDto>

    suspend fun getTVShowDetails(id: Int): Result<TVSearchResultsDto>

}