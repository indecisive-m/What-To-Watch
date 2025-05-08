package com.example.whattowatch.data.dto.tv_search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvSearchResultsDto(
    val page: Int,
    val results: List<SearchedTvDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)
