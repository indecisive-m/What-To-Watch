package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.tv_details.Seasons
import com.example.whattowatch.domain.model.Seasons as Domain

fun Seasons.toDomain(): Domain {
    return Domain(
        airDate = airDate,
        episodeCount = episodeCount,
        id = id,
        name = name,
        overview = overview,
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath",
        seasonNumber = seasonNumber,
        averageVote = averageVote
    )
}