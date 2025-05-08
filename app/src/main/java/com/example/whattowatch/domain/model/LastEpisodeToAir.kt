package com.example.whattowatch.domain.model

data class LastEpisodeToAir(
    val id: Int,
    val name: String,
    val overview: String,
    val averageVote: Double,
    val voteCount: Int,
    val airDate: String,
    val episodeNumber: String,
    val productionCode: String,
    val runtime: Int,
    val seasonNumber: Int,
    val showId: Int,
    val stillPath: String
)