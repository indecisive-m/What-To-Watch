package com.example.whattowatch.data.dto.tv_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvDetailsResultsDto(
    val page: Int,
    val results: List<TvDetails>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)
