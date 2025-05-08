package com.example.whattowatch.data.dto.movie_search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResultsDto(
    val page: Int,
    val results: List<SearchedMovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)
