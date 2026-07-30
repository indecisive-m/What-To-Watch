package com.example.whattowatch.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JustWatchDto(
    val link: String,
    val flatrate: List<JustWatchItem>? = emptyList(),
    val rent: List<JustWatchItem>? = emptyList(),
    val buy: List<JustWatchItem>? = emptyList()
)

@Serializable
data class JustWatchItem(
    @SerialName("logo_path")
    val logoPath: String,
    @SerialName("provider_id")
    val providerId: Int,
    @SerialName("provider_name")
    val providerName: String,
    @SerialName("display_priority")
    val displayPriority: Int,
)