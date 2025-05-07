package com.example.whattowatch.data.mappers

import com.example.whattowatch.data.dto.SearchedTVShowDto
import com.example.whattowatch.domain.TVShow


// Will need to add a null check for the images
fun SearchedTVShowDto.toTVShow(): TVShow {
    return TVShow(
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
