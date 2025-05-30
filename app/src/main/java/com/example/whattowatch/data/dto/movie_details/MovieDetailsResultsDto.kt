package com.example.whattowatch.data.dto.movie_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieDetailsResultsDto(
    val page: Int,
    val results: List<MovieDetailsDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int,
)


