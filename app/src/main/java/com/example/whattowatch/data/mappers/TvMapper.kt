package com.example.whattowatch.data.mappers

import com.example.whattowatch.data.dto.tv_details.TvDetailsDto
import com.example.whattowatch.data.dto.tv_search.SearchedTvDto
import com.example.whattowatch.data.mappers.helpers.toDomain
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.domain.TvDetails


// Will need to add a null check for the images
fun SearchedTvDto.toTv(): Tv {
    return Tv(
        id = id,
        name = name,
        adult = adult,
        backdropPath = "https://image.tmdb.org/t/p/w500$backdropPath",
        genreIds = genreIds,
        originCountry = originCountry,
        language = language,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath",
        firstAirDate = firstAirDate,
        averageVote = averageVote,
        voteCount = voteCount
    )
}

fun TvDetailsDto.toTvDetails(): TvDetails {
    return TvDetails(
        id = id,
        adult = adult,
        posterPath = "https://image.tmdb.org/t/p/w780$posterPath",
        backdropPath = "https://image.tmdb.org/t/p/w500$backdropPath",
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
