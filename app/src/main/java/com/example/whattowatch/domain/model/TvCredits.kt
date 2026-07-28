package com.example.whattowatch.domain.model


data class TvCredits(
    val tvCast: List<TvCast>,
    val tvCrew: List<TvCrew>

)

data class TvCast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?,
    val character: String,
    val creditId: String,
    val order: Int,
)

data class TvCrew(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?,
    val creditId: String,
    val department: String,
    val job: String,
)



