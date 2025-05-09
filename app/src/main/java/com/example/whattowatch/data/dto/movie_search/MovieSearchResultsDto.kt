package com.example.whattowatch.data.dto.movie_search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResultsDto(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<SearchedMovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)
