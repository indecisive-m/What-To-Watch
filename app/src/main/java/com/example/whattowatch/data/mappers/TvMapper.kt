package com.example.whattowatch.data.mappers

import com.example.whattowatch.data.dto.tv_search.SearchedTvDto
import com.example.whattowatch.domain.Tv


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

//fun TVShowDetails.toTVDetails()
