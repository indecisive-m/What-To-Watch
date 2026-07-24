package com.example.whattowatch.data.remote.mappers

import com.example.whattowatch.data.remote.dto.tv_details.TvDetailsDto
import com.example.whattowatch.data.remote.dto.tv_search.SearchedTvDto
import com.example.whattowatch.data.remote.mappers.helpers.toDomain
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.domain.TvDetails


// Will need to add a null check for the images
fun SearchedTvDto.toTv(): Tv {
    return Tv(
        id = id,
        name = name,
        adult = adult,
        backdropPath = backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        genreIds = genreIds,
        originCountry = originCountry,
        language = language,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        firstAirDate = firstAirDate,
        averageVote = averageVote,
        voteCount = voteCount
    )
}

fun TvDetailsDto.toTvDetails(): TvDetails {
    return TvDetails(
        id = id,
        adult = adult,
        posterPath = posterPath?.let { "https://image.tmdb.org/t/p/w780$it" },
        backdropPath = backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        popularity = popularity,
        overview = overview,
        language = language,
        averageVote = averageVote,
        voteCount = voteCount,
        createdBy = createdBy.map { it.toDomain() },
        episodeRuntime = episodeRuntime,
        firstAirDate = firstAirDate,
        genres = genres.map { it.toDomain() },
        homepage = homepage,
        inProduction = inProduction,
        languages = languages,
        lastAirDate = lastAirDate,
        lastEpisodeToAir = lastEpisodeToAir.toDomain(),
        name = name,
        nextEpisodeToAir = nextEpisodeToAir?.toDomain(),
        networks = networks.map { it.toDomain() },
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        originCountry = originCountry,
        originalName = originalName,
        productionCompanies = productionCompanies.map { it.toDomain() },
        productionCountries = productionCountries.map { it.toDomain() },
        seasons = seasons.map { it.toDomain() },
        spokenLanguages = spokenLanguages.map { it.toDomain() },
        status = status,
        tagline = tagline,
        type = type,
    )
}
