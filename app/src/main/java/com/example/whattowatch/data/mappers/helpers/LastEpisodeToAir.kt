package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.tv_details.LastEpisodeToAir
import com.example.whattowatch.domain.model.LastEpisodeToAir as Domain

fun LastEpisodeToAir.toDomain(): Domain {
    return Domain(
        id = id,
        name = name,
        overview = overview,
        averageVote = averageVote,
        voteCount = voteCount,
        airDate = airDate,
        episodeNumber = episodeNumber,
        productionCode = productionCode,
        runtime = runtime,
        seasonNumber = seasonNumber,
        showId = showId,
        stillPath = "https://image.tmdb.org/t/p/w300$stillPath"
    )
}