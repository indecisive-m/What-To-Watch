package com.example.whattowatch.data.dto.movie_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,
    val budget: Int,
    val genres: List<Genres>,
    val homepage: String,
    val id: Int,
    @SerialName("imdb_id")
    val imdbId: String?,
    @SerialName("original_language")
    val language: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanies>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountries>,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguages>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val averageVote: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class BelongsToCollection(
    val id: Int?,
    val name: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
)

@Serializable
data class Genres(
    val id: Int,
    val name: String
)

@Serializable
data class ProductionCompanies(
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String?,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)

@Serializable
data class ProductionCountries(
    @SerialName("iso_3166_1")
    val iso: String,
    val name: String
)

@Serializable
data class SpokenLanguages(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso: String,
    val name: String
)