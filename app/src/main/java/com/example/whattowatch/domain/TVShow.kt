package com.example.whattowatch.domain

data class TVShow(
    val id: Int,
    val name: String,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originCountry: String,
    val language: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val firstAirDate: String,
    val averageVote: Double,
    val voteCount: Int

)
