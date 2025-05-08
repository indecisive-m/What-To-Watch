package com.example.whattowatch.presentation.search_results

import com.example.whattowatch.domain.Media

data class SearchResultsState(
    val searchResults: List<Media> = emptyList<Media>()
)
