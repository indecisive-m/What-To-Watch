package com.example.whattowatch.domain

data class Movie(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val posterPath: String,
    val backdropPath: String,
    val genreIds: List<Int>,
    val language: String,
    val originalTitle: String?,
    val overview: String,
    val popularity: Double?,
    val releaseDate: String,
    val video: Boolean?,
    val averageVote: Double?,
    val voteCount: Int?
)