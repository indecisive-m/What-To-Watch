package com.example.whattowatch.data.dto.tv_details

import com.example.whattowatch.data.dto.movie_details.Genres
import com.example.whattowatch.data.dto.movie_details.ProductionCompanies
import com.example.whattowatch.data.dto.movie_details.ProductionCountries
import com.example.whattowatch.data.dto.movie_details.SpokenLanguages
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvDetailsDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("created_by")
    val createdBy: List<CreatedBy>,
    @SerialName("episode_runtime")
    val episodeRuntime: List<Int>,
    @SerialName("first_air_date")
    val firstAirDate: String,
    val genres: List<Genres>,
    val homepage: String,
    val id: Int,
    @SerialName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerialName("last_air_date")
    val lastAirDate: String?,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir,
    val name: String,
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAir?,
    val networks: List<Networks>,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerialName("origin_country")
    val originCountry: String,
    @SerialName("original_language")
    val language: String,
    @SerialName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanies>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountries>,
    val seasons: List<Seasons>,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguages>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerialName("vote_average")
    val averageVote: Double,
    @SerialName("vote_count")
    val voteCount: Int
)


@Serializable
data class CreatedBy(
    val id: Int,
    @SerialName("credit_id")
    val creditId: String,
    val name: String,
    val gender: Int,
    @SerialName("profile_path")
    val profilePath: String,
    @SerialName("original_name")
    val originalName: String
)


@Serializable
data class LastEpisodeToAir(
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("vote_average")
    val averageVote: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: String,
    @SerialName("production_code")
    val productionCode: String,
    val runtime: Int,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("show_id")
    val showId: Int,
    @SerialName("still_path")
    val stillPath: String
)

@Serializable
data class NextEpisodeToAir(
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("vote_average")
    val averageVote: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: String,
    @SerialName("episode_type")
    val episodeType: String,
    @SerialName("production_code")
    val productionCode: String,
    val runtime: Int,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("show_id")
    val showId: Int,
    @SerialName("still_path")
    val stillPath: String
)


@Serializable
data class Networks(
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)

@Serializable
data class Seasons(
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_count")
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("vote_average")
    val averageVote: Double,
)