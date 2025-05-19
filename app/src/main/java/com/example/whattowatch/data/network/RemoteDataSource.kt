package com.example.whattowatch.data.network

import com.example.whattowatch.data.dto.movie_details.MovieDetailsDto
import com.example.whattowatch.data.dto.movie_search.MovieSearchResultsDto
import com.example.whattowatch.data.dto.movie_upcoming.UpcomingMovieSearchResultsDto
import com.example.whattowatch.data.dto.tv_details.TvDetailsDto
import com.example.whattowatch.data.dto.tv_search.TvSearchResultsDto

interface RemoteDataSource {
    suspend fun searchMovies(
        query: String
    ): Result<MovieSearchResultsDto>

    suspend fun getImages(imageUrlString: String): Result<ByteArray>

    suspend fun searchTv(query: String): Result<TvSearchResultsDto>

    suspend fun getMovieDetails(id: Int): Result<MovieDetailsDto>

    suspend fun getTvDetails(id: Int): Result<TvDetailsDto>

    suspend fun getUpcomingMovies(): Result<UpcomingMovieSearchResultsDto>


}