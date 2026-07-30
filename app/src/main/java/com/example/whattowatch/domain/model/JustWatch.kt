package com.example.whattowatch.domain.model

data class JustWatch(
    val link: String?,
    val flatrate: List<JustWatchItem>?,
    val buy: List<JustWatchItem>?,
    val rent: List<JustWatchItem>?
)

data class JustWatchItem(
    val logoPath: String?,
    val providerId: Int,
    val providerName: String,
    val displayPriority: Int,
)