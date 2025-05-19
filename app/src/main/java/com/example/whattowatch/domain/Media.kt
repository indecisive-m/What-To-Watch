package com.example.whattowatch.domain

import com.example.whattowatch.domain.model.BelongsToCollection
import com.example.whattowatch.domain.model.CreatedBy
import com.example.whattowatch.domain.model.Genres
import com.example.whattowatch.domain.model.LastEpisodeToAir
import com.example.whattowatch.domain.model.Networks
import com.example.whattowatch.domain.model.NextEpisodeToAir
import com.example.whattowatch.domain.model.ProductionCompanies
import com.example.whattowatch.domain.model.ProductionCountries
import com.example.whattowatch.domain.model.Seasons
import com.example.whattowatch.domain.model.SpokenLanguages

sealed class Media {
    abstract val id: Int
    abstract val adult: Boolean
    abstract val posterPath: String
    abstract val backdropPath: String
    abstract val popularity: Double
    abstract val overview: String
    abstract val language: String
    abstract val averageVote: Double
    abstract val voteCount: Int
}


data class Movie(
    override val id: Int,
    override val adult: Boolean,
    override val posterPath: String,
    override val backdropPath: String,
    override val popularity: Double,
    override val overview: String,
    override val language: String,
    override val averageVote: Double,
    override val voteCount: Int,

    val genreIds: List<Int>,
    val title: String?,
    val originalTitle: String?,
    val releaseDate: String?,
    val video: Boolean?,
) : Media()


data class Tv(
    override val id: Int,
    override val adult: Boolean,
    override val posterPath: String,
    override val backdropPath: String,
    override val popularity: Double,
    override val overview: String,
    override val language: String,
    override val averageVote: Double,
    override val voteCount: Int,

    val genreIds: List<Int>,
    val name: String?,
    val originCountry: String?,
    val originalName: String?,
    val firstAirDate: String?,
) : Media()

data class MovieDetails(
    override val id: Int,
    override val adult: Boolean,
    override val posterPath: String,
    override val backdropPath: String,
    override val popularity: Double,
    override val overview: String,
    override val language: String,
    override val averageVote: Double,
    override val voteCount: Int,

    val belongsToCollection: BelongsToCollection?,
    val budget: Int,
    val genres: List<Genres>,
    val homepage: String,
    val imdbId: String,
    val originalTitle: String,
    val productionCompanies: List<ProductionCompanies>,
    val productionCountries: List<ProductionCountries>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguages>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
) : Media()

data class TvDetails(
    override val id: Int,
    override val adult: Boolean,
    override val posterPath: String,
    override val backdropPath: String,
    override val popularity: Double,
    override val overview: String,
    override val language: String,
    override val averageVote: Double,
    override val voteCount: Int,

    val createdBy: List<CreatedBy>,
    val episodeRuntime: List<Int>,
    val firstAirDate: String,
    val genres: List<Genres>,
    val homepage: String,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String?,
    val lastEpisodeToAir: LastEpisodeToAir,
    val name: String,
    val nextEpisodeToAir: NextEpisodeToAir?,
    val networks: List<Networks>,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: String,
    val originalName: String,
    val productionCompanies: List<ProductionCompanies>,
    val productionCountries: List<ProductionCountries>,
    val seasons: List<Seasons>,
    val spokenLanguages: List<SpokenLanguages>,
    val status: String,
    val tagline: String,
    val type: String,

    ) : Media()


