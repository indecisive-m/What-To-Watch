package com.example.whattowatch.data.network

import com.example.whattowatch.data.dto.MovieSearchResultsDto

interface RemoteDataSource {
    suspend fun searchMovies(
        query: String
    ): MovieSearchResultsDto
}