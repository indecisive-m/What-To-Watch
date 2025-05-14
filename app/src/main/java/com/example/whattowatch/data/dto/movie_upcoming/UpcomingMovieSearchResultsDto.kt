package com.example.whattowatch.data.dto.movie_upcoming

import com.example.whattowatch.data.dto.movie_search.SearchedMovieDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingMovieSearchResultsDto(
    @SerialName("dates")
    val dates: Dates,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<SearchedMovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)