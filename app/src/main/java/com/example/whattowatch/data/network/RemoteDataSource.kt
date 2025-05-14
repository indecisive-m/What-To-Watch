package com.example.whattowatch.data.network

import com.example.whattowatch.data.dto.movie_details.MovieDetailsResultsDto
import com.example.whattowatch.data.dto.movie_search.MovieSearchResultsDto
import com.example.whattowatch.data.dto.movie_upcoming.UpcomingMovieSearchResultsDto
import com.example.whattowatch.data.dto.tv_details.TvDetailsResultsDto
import com.example.whattowatch.data.dto.tv_search.TvSearchResultsDto

interface RemoteDataSource {
    suspend fun searchMovies(
        query: String
    ): Result<MovieSearchResultsDto>

    suspend fun getImages(imageUrlString: String): Result<ByteArray>

    suspend fun searchTv(query: String): Result<TvSearchResultsDto>

    suspend fun getMovieDetails(id: Int): Result<MovieDetailsResultsDto>

    suspend fun getTvDetails(id: Int): Result<TvDetailsResultsDto>

    suspend fun getUpcomingMovies(): Result<UpcomingMovieSearchResultsDto>


}