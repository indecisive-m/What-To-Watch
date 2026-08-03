package com.example.whattowatch.domain.model

data class Images(
    val aspectRatio: Double,
    val height: Int,
    val iso3166: String?,
    val iso639: String?,
    val filePath: String?,
    val averageVote: Double,
    val voteCount: Int,
    val width: Int

)
